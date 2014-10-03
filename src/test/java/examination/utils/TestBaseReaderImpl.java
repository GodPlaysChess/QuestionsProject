package examination.utils;

import examination.DataLayer.dao.AbstractTest;
import examination.DataLayer.models.Question;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

public class TestBaseReaderImpl extends AbstractTest<Question> {
    @Test
    public void test() throws IOException {
        BaseReader r = new BaseReaderImpl(1);
        r.read("db_text/filo.txt");
        List<Question> questions = r.getQuestions();
        r.print();
    }
}