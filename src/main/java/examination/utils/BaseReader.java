package examination.utils;

import examination.DataLayer.models.Question;

import java.io.IOException;
import java.util.List;

/**
 * author: a.savanovich
 * Date: 04.10.14
 * Time: 0:21
 * To change this template use File | Settings | File Templates.
 */
public interface BaseReader {
    void read(String filename) throws IOException;
    List<Question> getQuestions();

    void print();
}
