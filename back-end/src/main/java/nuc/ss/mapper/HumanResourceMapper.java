package nuc.ss.mapper;

import nuc.ss.pojo.AdminHumanResource;
import nuc.ss.pojo.HumanResource;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface HumanResourceMapper {
    List<HumanResource> queryHRList();

    HumanResource queryHRById(Integer id);

    int addHR(HumanResource humanResource);

    int updateHR(HumanResource humanResource);

    int deleteHR(int id);
}
