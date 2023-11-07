package persistence.dao;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import persistence.dto.InsertQuestionDTO;
import persistence.dto.QuestionDTO;

import java.util.List;
import java.util.Map;

public class QuestionDAO {
    private final SqlSessionFactory sqlSessionFactory;

    public QuestionDAO(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }

    public int insertQuestion(InsertQuestionDTO insertQuestionDTO) {
        SqlSession session = sqlSessionFactory.openSession();
        int affectedRows = 0;
        try {
            affectedRows = session.insert("mapper.QuestionMapper.insertQuestion", insertQuestionDTO);
            session.commit();
        } finally {
            session.close();
        }
        return affectedRows;
    }

    public int countRows() {
        SqlSession session = sqlSessionFactory.openSession();
        int rowCount = 0;
        try {
            rowCount = session.selectOne("mapper.QuestionMapper.countRows");
        } finally {
            session.close();
        }
        return rowCount;
    }

    public List<QuestionDTO> getQuestion(Map<String, Object> params) {
        List<QuestionDTO> list = null;
        SqlSession session = sqlSessionFactory.openSession();
        try {
            list = session.selectList("mapper.QuestionMapper.getQuestion", params);
        } finally {
            session.close();
        }
        return list;
    }

    public List<QuestionDTO> getAllQuestion() {
        List<QuestionDTO> list = null;
        SqlSession session = sqlSessionFactory.openSession();
        try {
            list = session.selectList("mapper.QuestionMapper.getAllQuestion");
        } finally {
            session.close();
        }
        return list;
    }
}
