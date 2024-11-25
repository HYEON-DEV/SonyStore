package kr.co.sonystore.services;
import kr.co.sonystore.exceptions.ServiceNoResultException;
import kr.co.sonystore.models.Member;

import java.util.List;

public interface MemberService {
    public Member addMember(Member input) throws ServiceNoResultException, Exception;

    public Member editMember(Member input) throws ServiceNoResultException, Exception;

    public int deleteMember(Member input) throws ServiceNoResultException, Exception;

    public Member getMember(Member input) throws ServiceNoResultException, Exception;

    public void isUniqueEmail(Member input) throws Exception;

    public Member findEmail(Member input) throws Exception;

    public Member findUserPw(Member input) throws Exception;

    public void resetPw(Member input) throws Exception;

    public Member login(Member input) throws Exception;

    public int out (Member input) throws Exception;

    public List<Member> deleteOutMembers() throws Exception;
}
        
