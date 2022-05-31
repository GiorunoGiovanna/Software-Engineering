package nuc.ss.mapper;

import nuc.ss.pojo.AdminUser;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface AdminUserMapper {
    List<AdminUser> queryUserList();

    AdminUser queryUserByName(String name);

    AdminUser queryUserById(int id);

    int addUser(AdminUser adminUser);

    int updateUser(AdminUser adminUser);

    int deleteUser(int id);
}
