package SpringBoot.mapper;


import SpringBoot.bean.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MyMapper {

    public User selectUser(int id);

}