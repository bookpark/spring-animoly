package toy.animoly.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import toy.animoly.entity.Member;

public interface MemberRepository extends JpaRepository<Member, String> {
}
