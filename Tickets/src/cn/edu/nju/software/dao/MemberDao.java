package cn.edu.nju.software.dao;

import cn.edu.nju.software.models.Member;

import java.util.List;

public interface MemberDao {

    public void saveMember(Member member)throws Exception;

    public Member findMember(String email) throws Exception;

    public void updateMember(Member member) throws Exception;

    public List<Member> getAllMembers() throws Exception;
}
