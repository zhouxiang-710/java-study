package com.base.Vo;


import lombok.Data;

/**
 * @ Author     ：zhouxiang.
 * @ Date       ：Created in 15:41 2020/7/1
 * @ Description：${description}
 * @ Modified By：
 * @Version: $version$
 */
@Data
public class GradeVo {
    private String studentNumber;

    private String studentName;
    private Integer gradeId;
    private String gradeName;

    public boolean isHighGrade(){
        if(this.gradeId>11){
            return true;
        }
        return false;
    }


    @Override
    public String toString() {
        return "GardeVo{" +
                "studentNumber='" + studentNumber + '\'' +
                ", studentName='" + studentName + '\'' +
                ", gradeId=" + gradeId +
                ", gradeName='" + gradeName + '\'' +
                '}';
    }
}
