package kr.co.sonystore.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.sonystore.exceptions.ServiceNoResultException;
import kr.co.sonystore.mappers.MemberMapper;
import kr.co.sonystore.models.Member;
import kr.co.sonystore.services.MemberService;
import lombok.extern.slf4j.Slf4j;

/**
 * (1) 모든 메서드를 재정의 한 직후 리턴값 먼저 정의
 */
@Slf4j
@Service
public class MemberServiceImpl implements MemberService {

    @Autowired
    private MemberMapper memberMapper;

    @Override
    public Member addMember(Member input) throws ServiceNoResultException, Exception {
        int rows = memberMapper.insert(input);
        

        if(rows == 0) {
            throw new ServiceNoResultException("저장된 데이터가 없습니다.");
        }

        return memberMapper.selectMember(input);
    }

    @Override
    public Member editMember(Member input) throws ServiceNoResultException, Exception {
        int rows = 0;

        try {
            rows = memberMapper.update(input);

            //wherw절 조건에 맞는 데이터가 없는 경우 --> 비밀번호 잘못됨
            if(rows == 0) {
                throw new Exception("현재 비밀번호를 확인하세요.");
            }
        } catch (Exception e) {
            log.error("Member 데이터 수정에 실패했습니다.", e);
            throw e;
        }

        return memberMapper.selectMember(input);
    }

    @Override
    public int deleteMember(Member input) throws ServiceNoResultException, Exception {
        // delete문 수행 --> 리턴되는 값은 수정된 데이터의 수
        int rows = memberMapper.delete(input);

        if(rows == 0) {
            throw new ServiceNoResultException("삭제된 데이터가 없습니다.");
        }

        return rows;
    }

    @Override
    public Member getMember(Member input) throws ServiceNoResultException, Exception {
        Member output = memberMapper.selectMember(input);

        if(output == null) {
            throw new ServiceNoResultException("조회된 데이터가 없습니다.");
        }

        return output;
    }

    @Override
    public void isUniqueEmail(Member input) throws Exception {
        int output = 0;

        try{
            output = memberMapper.selectCount(input);

            if (output > 0) {
                throw new Exception("사용할 수 없는 이메일 입니다.");
            }
        } catch (Exception e) {
            log.error("이메일 중복 검사에 실패했습니다.", e);
            throw e;
        }
    }

    @Override
    public Member findEmail(Member input) throws Exception {
        
        Member output = null;

        try {
            output = memberMapper.findEmail(input);

            if(output == null) {
                throw new Exception("가입된 정보가 없습니다.");
            }
        } catch (Exception e) {
            log.error("아이디 찾기에 실패했습니다.", e);
            throw e;
        }
        return output;
    }

    @Override
    public Member findUserPw(Member input) throws Exception {
        
        Member output = null;

        try {
            output = memberMapper.findUserPw(input);

            if(output == null) {
                throw new Exception("가입된 정보가 없습니다.");
            }
        } catch (Exception e) {
            log.error("비밀번호 찾기에 실패했습니다.", e);
            throw e;
        }
        return output;
    }

    @Override
    public void resetPw(Member input) throws Exception {
        int rows = 0;

        try {
            rows = memberMapper.resetPw(input);

            if(rows == 0) {
                throw new Exception("아이디와 비밀번호를 확인하세요.");
            }
        } catch (Exception e) {
            log.error("비밀번호 수정에 실패했습니다.", e);
            throw e;
        }
    
    }

    @Override
    public Member login(Member input) throws Exception {
        Member output = null;

        try {
            output = memberMapper.login(input);

            if(output == null) {
                throw new Exception("아이디 혹은 이메일을 확인하세요.");
            }
        } catch (Exception e) {
            log.error("아이디 조회에 실패했습니다.", e);
            throw e;
        }
        // 데이터 조회에 성공했다면 마지막 로그인 시간 갱신
        try {
            int rows = memberMapper.updateLoginDate(output);

            if(rows == 0) {
                throw new Exception("존재하지 않는 회원에 대한 요청 입니다.");
            }
        } catch (Exception e) {
            log.error("로그인 시간 갱신에 실패했습니다.", e);
            throw e;
        }

        return output;
    }

    
    @Override
    public int out(Member input) throws Exception {
        int rows = 0;

        try {
            rows = memberMapper.out(input);
            if (rows == 0) {
                throw new Exception("비밀번호 확인이 잘못되었거나 존재하지 않는 회원에 대한 요청입니다");
            }
        } catch (Exception e) {
            log.error("회원탈퇴에 실패했습니다.", e);
        }
        

        return rows;
    }

    @Override
    public List<Member> deleteOutMembers() throws Exception {
        List<Member> output = null;

        try {
            memberMapper.deleteOutMembers();
        } catch (Exception e) {
            throw new Exception("탈퇴 처리에 실패했습니다.");
        }
        
        return output;

    }
}
