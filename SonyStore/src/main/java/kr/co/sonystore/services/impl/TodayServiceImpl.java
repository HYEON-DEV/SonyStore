package kr.co.sonystore.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.sonystore.mappers.TodayMapper;
import kr.co.sonystore.models.TodayMember;
import kr.co.sonystore.services.TodayService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class TodayServiceImpl implements TodayService {

    @Autowired
    private TodayMapper todayMapper;

    @Override
    public int newMemberCount() throws Exception {
        int rows = 0;

        try {
            rows = todayMapper.insertNewMemberCount();
        } catch (Exception e) {
            log.error("추가에 실패했습니다.", e);
            throw e;
        }

        return rows;
    }

    @Override
    public List<TodayMember> selectWeekMemberCount() throws Exception {
        List<TodayMember> result = null;

        try {
            result = todayMapper.selectWeekMemberCount();
        } catch (Exception e) {
            log.error("조회에 실패했습니다.", e);
            throw new Exception("조회에 실패했습니다.");
        }

        return result;
    }

    @Override
    public List<TodayMember> selectMonthMemberCount() throws Exception {
        List<TodayMember> result = null;

        try {
            result = todayMapper.selectMonthMemberCount();
        } catch (Exception e) {
            log.error("조회에 실패했습니다.", e);
            throw new Exception("조회에 실패했습니다.");
        }

        return result;
    }
}
