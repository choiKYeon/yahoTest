package org.example.member.service;

import jdk.jshell.execution.Util;
import org.example.Utill.Utill;
import org.example.member.entity.Member;

import java.util.ArrayList;
import java.util.List;
public class MemberService {
    List<Member> members = new ArrayList<>();

    public MemberService(){
        Member member1 = new Member(1, "홍길동", "1234", Utill.nowDateTime());
        members.add(member1);
        Member member2 = new Member(2, "홍길순", "12345", Utill.nowDateTime());
        members.add(member2);
        Member member3 = new Member(3, "임꺽정", "123456", Utill.nowDateTime());
        members.add(member3);
    }

    public String signService(String userId, String password){
        long id = 3;
        Member member = new Member(id, userId, password, Utill.nowDateTime());
        id++;
        members.add(member);

        return member.getUserId();
    }

    public Member findByUserId(String userId){
        for (int i = 0; i < members.size(); i++) {
            Member member = members.get(i);
            if (member.getUserId().equals(userId)) {
                return member;
            }
        }
        return null;
    }
}
