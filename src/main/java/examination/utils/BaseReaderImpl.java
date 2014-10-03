package examination.utils;

import examination.DataLayer.models.Question;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * author: a.savanovich
 * Date: 04.10.14
 * Time: 0:26
 * To change this template use File | Settings | File Templates.
 */
public class BaseReaderImpl implements BaseReader {
    private List<Question> questions;
    private long courseId;
    private long currentZoneNumber;
    public BaseReaderImpl(long courseId) {
        this.courseId = courseId;
        questions = new ArrayList<Question>();
    }
    @Override
    public void read(String filename) throws IOException {
        String[] lines = StringUtils.split(FileUtils.readFileToString(new File(filename)), '@');
        currentZoneNumber = 0;
        for (String line: lines) {
            String[] questionsTexts = StringUtils.splitByWholeSeparator(line, IOUtils.LINE_SEPARATOR_WINDOWS + IOUtils.LINE_SEPARATOR_WINDOWS);
            for (String questionText : questionsTexts) {
                Question question = new Question();
                questionText = StringUtils.trim(questionText);
                if (StringUtils.isNotBlank(questionText)) {
                    question.setText(StringUtils.trim(questionText));
                    question.setZoneId(currentZoneNumber);
                    question.setCourseId(courseId);
                    questions.add(question);
                }
            }
            currentZoneNumber++;
        }
    }

    @Override
    public List<Question> getQuestions() {
        return questions;
    }

    @Override
    public void print() {
        for(Question q: questions) {
            System.out.println(q.getZoneId());
            System.out.println(q.getText());
            System.out.println();
        }
    }
}
