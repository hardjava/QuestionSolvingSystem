package persistence.dao;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import persistence.dto.InsertObtDTO;
import persistence.dto.ObtDTO;

import java.util.List;
import java.util.Map;

public class ObtDAO {
    private final SqlSessionFactory sqlSessionFactory;

    public ObtDAO(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }

    public int insertObt(InsertObtDTO insertObtDTO) {
        SqlSession session = sqlSessionFactory.openSession();
        int affectedRows = 0;
        try {
            affectedRows = session.insert("mapper.ObtMapper.insertObt", insertObtDTO);
            session.commit();
        } finally {
            session.close();
        }
        return affectedRows;
    }

    public void printObt(Map<String, Object> params) {
        List<ObtDTO> list = null;
        SqlSession session = sqlSessionFactory.openSession();
        try {
            list = session.selectList("mapper.ObtMapper.printObt", params);
        } finally {
            session.close();
        }
        for (int i = 0; i < list.size(); i++) {
            System.out.println((i + 1) + "번 > " + list.get(i).getOptText());
        }
    }
}
