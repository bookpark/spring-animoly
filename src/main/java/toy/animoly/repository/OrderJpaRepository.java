package toy.animoly.repository;

import com.querydsl.core.types.dsl.ListPath;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;
import toy.animoly.entity.*;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class OrderJpaRepository {
    private final EntityManager em;
    private final JPAQueryFactory query;

    public OrderJpaRepository(EntityManager em) {
        this.em = em;
        this.query = new JPAQueryFactory(em);
    }

    public JPAQuery<Order> findAllWithItemQuerydsl() {
        QOrder o = QOrder.order;
        QMember m = o.member;
        QDelivery d = o.delivery;
        ListPath<OrderItem, QOrderItem> oi = o.orderItems;

        return query
                .select(o)
                .from(o)
                .join(m)
                .join(d)
                .join(oi)
                .fetchAll();
    }

    public List<Order> findAllWithItemJPQL() {
        return em.createQuery(
                        "select distinct o from Order o" +
                                " join fetch o.member m" +
                                " join fetch o.delivery d" +
                                " join fetch o.orderItems oi" +
                                " join fetch oi.item i", Order.class)
                .getResultList();
    }
}
