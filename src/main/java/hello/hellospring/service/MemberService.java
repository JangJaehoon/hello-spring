package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;

import java.util.List;
import java.util.Optional;

public class MemberService {

    private final MemberRepository memberRepository; // = new MemoryMemberRepository();

    MemberService(MemberRepository memberRepository){
        this.memberRepository = memberRepository;
    } // Dependency Injection : MemberService의 입장에선 MemberRepository를 넣어줌.

    /**
     * 회원 가입
     * @param member
     * @return
     */
    public Long join(Member member){
        // 이름이 중복되는 경우, 방지
        validateDuplicatingMember(member); // ifPresent메서드 는 이미 값이 존재하는지 확인하는 메서드

        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicatingMember(Member member) {
        memberRepository.findByName(member.getName())
        .ifPresent(m ->{
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }); // validateDuplicatingMember

    }

    /**
      *  전체 회원 조회
      */    
    public List<Member> findMembers(){
       return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId){
       return memberRepository.findById(memberId);
    }

}
