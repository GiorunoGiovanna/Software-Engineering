package nuc.ss.mapper;

import nuc.ss.pojo.AdminPayment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Mapper
@Repository
public interface AdminPaymentMapper {
    List<AdminPayment> queryAPList();

    List<AdminPayment> queryAPBykeyword(String keyword);

    List<AdminPayment> queryAPById(int id);

    //多参数使用注解
    List<AdminPayment> queryAPByTime(@Param("start") String start,
                                     @Param("end") String end);

    int addAP(AdminPayment adminPayment);

    int updateAP(AdminPayment adminPayment);

    int deleteAP(int id);
}
