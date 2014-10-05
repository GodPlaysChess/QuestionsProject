package examination.DataLayer.dao.mybatis;

import java.lang.reflect.Field;

import static com.google.common.base.CaseFormat.*;

/**
 * User: a.savanovich
 * Date: 20.06.12
 * Time: 18:33
 */
public class IbatisGenerator {

    public static String generateAll(Class c, String dbName) {
        StringBuilder builder = new StringBuilder();
        builder.append(BEGIN).append('\n');
        generateIbatis(c, builder);
        generateDbInsert(c, dbName, builder);
        generateDbSelectList(c, dbName, builder);
        generateDbSelectOne(c, dbName, builder);
        generateDbDelete(c, dbName, builder);
        generateDbUpdate(c, dbName, builder);
        builder.append(END).append('\n');
        return builder.toString();
    }

    public static void generateIbatis(Class c, StringBuilder builder) {
        builder.append(generateHeader(c));
        Field[] fields = c.getDeclaredFields();
        for (Field field : fields) {
            Class fieldType = field.getType();
            String name = generateLine(field.getName(), fieldType);
            if(name.equals("int")) {
            }
            builder.append(name).append('\n');
        }
        builder.append("</resultMap>\n");
    }


    private static String getShemeName(String dbName) {
        int dotIndex = dbName.indexOf('.');
        if (dotIndex > 0) {
            return dbName.substring(0, dotIndex);
        }
        return dbName;
    }

    public static void generateDbInsert(Class c, String dbName, StringBuilder builder) {
        Field[] fields = c.getDeclaredFields();
        String seqFieldName = fields[0].getName();
        String seqName = getShemeName(dbName) + ".seq_" + camelToUnderscore(seqFieldName);
        builder.append(generateDbHeader(c.getName(), seqName, seqFieldName, dbName)).append('\n');
        generateInsertList(c, builder);
        builder.append("\t\t) VALUES (").append('\n');
        generateInsertList2(c, builder);
        builder.append("\t)\n </insert>").append('\n');
    }

    public static void generateDbSelectList(Class c, String dbName, StringBuilder builder) {
        String query = "<select id=\"select" +
                generateShortName(c.getName()) +
                "List\" resultMap=\"" +
                generateResultMapName(c) +
                "\" parameterType=\"map\">\n" +
                "          SELECT *\n" +
                "            FROM " +
                dbName +
                "\n" +
                "        ORDER BY time_added DESC\n" +
                "        <if test=\"limit > 0\">\n" +
                "          OFFSET #{offset}\n" +
                "           LIMIT #{limit}\n" +
                "        </if>;\n" +
                " </select>";

        builder.append(query).append('\n');
    }

    public static void generateDbSelectOne(Class c, String dbName, StringBuilder builder) {
        Field[] fields = c.getDeclaredFields();
        String mainField = fields[0].getName();
        String query = "<select id=\"select" +
                generateShortName(c.getName()) +
                "ById\" resultMap=\"" +
                generateResultMapName(c) +
                "\" parameterType=\"map\">\n" +
                "        SELECT *\n" +
                "          FROM " +
                dbName +
                "\n" +
                "         WHERE " +
                getDbFieldName(mainField) +
                " = #{" +
                mainField +
                "};\n" +
                "</select>";

        builder.append(query).append('\n');

    }

    public static void generateDbDelete(Class c, String dbName, StringBuilder builder) {
        Field[] fields = c.getDeclaredFields();
        String mainField = fields[0].getName();
        String query = "<delete id=\"delete" +
                generateShortName(c.getName()) +
                "\" parameterType=\"map\">\n" +
                "        DELETE FROM " +
                dbName +
                "\n" +
                "              WHERE " +
                getDbFieldName(mainField) +
                " = #{" +
                mainField +
                "};\n" +
                "</delete>";
        builder.append(query).append('\n');
    }

    public static void generateDbUpdate(Class c, String dbName, StringBuilder builder) {
        Field[] fields = c.getDeclaredFields();
        String mainField = fields[0].getName();

        String updatedFields = "";
        int size = fields.length - 1;
        for (int i = 1; i < fields.length; ++i) {
            updatedFields += "\t\t\t\t" + getDbFieldName(fields[i].getName()) +
                    " = #{" +
                    getJavaName(fields[i]) +
                    "}";
            --size;
            if (size > 0) updatedFields += ",";
            updatedFields += "\n";
        }


        String query = "<update id=\"update" +
                generateShortName(c.getName()) +
                "\" parameterType=\"map\">\n" +
                "        UPDATE " +
                dbName +
                "\n" +
                "           SET \n" +
                           updatedFields +
                "         WHERE " +
                getDbFieldName(mainField) +
                " = #{" +
                mainField +
                "};\n" +
                "</update>";
        builder.append(query).append('\n');
    }

    private static void generateInsertList2(Class c, StringBuilder builder) {
        Field[] fields = c.getDeclaredFields();
        int size = fields.length;
        for (Field field : fields) {
            String name = "\t#{" + getJavaName(field) + "}";
            --size;
            if (size > 0 ) {
                name += ",";
            }
            builder.append(name).append('\n');
        }
    }

    private static void generateInsertList(Class c, StringBuilder builder) {
        Field[] fields = c.getDeclaredFields();
        int size = fields.length;
        for (Field field : fields) {
            String name = "\t" + getDbFieldName(field.getName());
            --size;
            if (size > 0 ) {
                name += ",";
            }

            builder.append(name).append('\n');
        }

    }


    private static String generateDbHeader(String name, String seqName, String seqFieldName, String dbName) {
        String result =     "<insert id=\"insert"+generateShortName(name)+ "\" parameterType=\"" + name +"\">\n" +
        "<selectKey keyProperty=\"" + seqFieldName+ "\" resultType=\"long\" order=\"BEFORE\">\n" +
        "        SELECT nextval('"+ seqName + "');\n" +
         "</selectKey>" +
        "\n" +
        "        INSERT INTO " + dbName+ " (";
        return result;
    }

    private static String generateHeader(Class c) {
        String result = "<resultMap id=\""+ generateResultMapName(c) + "\" type=\"" + c.getName() + "\">\n";
        return result;
    }

    private static String generateResultMapName(Class c) {
        return generateShortName(c.getName()) + "ResultMap";
    }

    private static String generateShortName(String name) {
        int dotIndex = name.lastIndexOf('.');
        if (dotIndex > 0) {
            return name.substring(dotIndex + 1);
        }
        return name;
    }

    private static String generateLine(String name, Class fieldType) {
        return generateLineInner(generateShortName(name), fieldType);
    }

    private static String generateLineInner(String name, Class fieldType) {
        String result = "<result property=\"" + getJavaName(name, fieldType) + "\" column=\"" + camelToUnderscore(name) + "\"/>";
        return result;
    }

    private static String getJavaName(Field field) {
        return getJavaName(generateShortName(field.getName()), field.getType());
    }

    private static String getJavaName(String name, Class fieldType) {
        if (fieldType.isEnum()) {
            return name + "Code";
        }
        return name;
    }

    private static String camelToUnderscore(String str) {
        return LOWER_CAMEL.to(LOWER_UNDERSCORE, str);
    }

    private static String getDbFieldName(String name) {
        return camelToUnderscore(generateShortName(name));
    }

    private static String BEGIN = "<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\n" +
            "<!DOCTYPE mapper\n" +
            "PUBLIC \"-//mybatis.org//DTD Mapper 3.0//EN\"\n" +
            "\"http://mybatis.org/dtd/mybatis-3-mapper.dtd\">\n" +
            "<mapper namespace=\"cluster.commondb\">";
    private static String END = "</mapper>";
}
