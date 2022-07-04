package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

// import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {

    MemberService memberService; // = new MemberService();
    MemoryMemberRepository memberRepository = new MemoryMemberRepository();

    @BeforeEach
    public void beforeEach(){
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
        // Dependency Injection을 활용하여, MemberService에
        // MemoryMemberRepository의 instance를 넣어줌.
    }


    @AfterEach
    public void afterEach(){
        memberRepository.clearStore();
    }
    // 각 메모리를 지워주는 기능. 메모리 누적이 없어서,
    // 한 인스턴스에 같은 파라미터를 사용하여 중복된 값으로 인식되는 걸 방지해줌.

    @Test
    void join() { // 회원 가입
        // given
        Member member = new Member();
        member.setName("spring");

        // when
        Long saveId = memberService.join(member);

        // then
        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());

    }

    @Test
    public void duplicating_Member_Exception(){
        // given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        // when
        memberService.join(member1);
        // assertThrows(IllegalStateException.class, () -> memberService.join(member2) );

        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2) );
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");

        /*
        try{
            memberService.join(member2);
            fail("Exception should be occured.");
        }catch (IllegalStateException e){
            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
        }
         */



        // then

    }


    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}