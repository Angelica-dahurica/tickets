package cn.edu.nju.software.dao.impl;

import cn.edu.nju.software.dao.BaseDao;
import cn.edu.nju.software.dao.MemberDao;
import cn.edu.nju.software.models.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class MemberDaoImpl implements MemberDao {

    @Autowired
    private BaseDao baseDao;

    public BaseDao getBaseDao() {
        return baseDao;
    }

    public void setBaseDao(BaseDao baseDao) {
        this.baseDao = baseDao;
    }

    @Override
    public void saveMember(Member member) throws Exception {
        baseDao.save(member);
    }

    @Override
    public Member findMember(String email) throws Exception {
        return (Member) baseDao.load(Member.class, email);
    }

    @Override
    public void updateMember(Member member) throws Exception {
        baseDao.update(member);
    }

    @Override
    public List<Member> getAllMembers() throws Exception {
        List list = baseDao.getAllList(Member.class);
        List<Member> members = new ArrayList<>();
        for(Object o : list) {
            members.add((Member) o);
        }
        return members;
    }

}
