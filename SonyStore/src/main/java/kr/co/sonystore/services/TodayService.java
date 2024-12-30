package kr.co.sonystore.services;

import java.util.List;

import kr.co.sonystore.models.TodayMember;

public interface TodayService {

    public int newMemberCount() throws Exception;

    public List<TodayMember> selectWeekMemberCount() throws Exception;

    public List<TodayMember> selectMonthMemberCount() throws Exception;
}
