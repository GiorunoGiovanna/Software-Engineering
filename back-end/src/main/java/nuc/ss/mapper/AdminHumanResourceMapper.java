package nuc.ss.mapper;


import nuc.ss.pojo.AdminHumanResource;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface AdminHumanResourceMapper {
    List<AdminHumanResource> queryAHRList();

    AdminHumanResource queryAHRById(Integer id);

    int addAHR(AdminHumanResource adminHumanResource);

    int updateAHR(AdminHumanResource adminHumanResource);

    int deleteAHR(int id);
}
