package member.service;

import member.model.Member;

public interface MemberService {
    void save(Member member);
    Member findByUserName(String username);
    Member findByUserEmail(String email);
}
