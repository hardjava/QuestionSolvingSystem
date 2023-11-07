package persistence.dao;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import persistence.dto.CategoryDTO;

import java.util.List;

public class CategoryDAO {
    private final SqlSessionFactory sqlSessionFactory;

    public CategoryDAO(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }

    public List<CategoryDTO> selectAll() {
        List<CategoryDTO> list = null;
        SqlSession session = sqlSessionFactory.openSession();
        try {
            list = session.selectList("mapper.CategoryMapper.selectAll");
        } finally {
            session.close();
        }
        return list;
    }
}
