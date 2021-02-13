package org.zerock.controller.dto.board;

import org.springframework.data.jpa.domain.Specification;
import org.zerock.controller.domain.board.Board;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BoardSpecs {
    public static Specification<Board> searchWith(Map<String, Object> filter){
        return (root, query, cb)->{
            List<Predicate> predicate=new ArrayList<>();

            filter.forEach((key, value)->{
                String likeValue="%"+value+"%";

                switch(key){
                    case "title":
                        predicate.add(cb.like(root.get(key).as(String.class),likeValue));
                        break;

                    case "content":
                        predicate.add(cb.like(root.get(key).as(String.class),likeValue));
                        break;

                    case "writer":
                        predicate.add(cb.like(root.get(key).as(String.class),likeValue));
                        break;
                }
            });

            return cb.or(predicate.toArray(new Predicate[0]));
        };
    }

//    public static Specification<SystemUseHistory> searchSystemUseHistory(Map<String, Object> filter) {
//        return (root, query, cb) -> {
//            List<Predicate> predicates = new ArrayList<>();
//
//            query.orderBy(cb.desc(root.get("createdAt")));
//
//            filter.forEach((key, value) -> {
//                String likeValue = "%" + value + "%";
//
//                switch (key) {
//                    case "gameName":
//                        Predicate gameNamePredicate = cb.like(root.get("game").get("name").as(String.class), likeValue);
//                        predicates.add(gameNamePredicate);
//                        break;
//                    case "companyName":
//                        Predicate companyNamePredicate = cb.like(root.get("member").get("companyInfo").get("name").as(String.class), likeValue);
//                        predicates.add(companyNamePredicate);
//                        break;
//                    case "startDate":
//                        Predicate startDatePredicate = cb.greaterThanOrEqualTo(root.get("crSeatedAt"), (Date) value);
//                        predicates.add(startDatePredicate);
//                        break;
//                    case "endDate":
//                        Predicate endDatePredicate = cb.lessThanOrEqualTo(root.get("createdAt"), (Date) value);
//                        predicates.add(endDatePredicate);
//                        break;
//                    case "memberName":
//                        Predicate memberNamePredicate = cb.like(root.get("member").get("name").as(String.class), likeValue);
//                        predicates.add(memberNamePredicate);
//                        break;
//                    case "uri":
//                        Predicate uriPredicate = cb.like(root.get("uri").as(String.class), likeValue);
//                        predicates.add(uriPredicate);
//                        break;
//                }
//            });
//
//            return cb.and(predicates.toArray(new Predicate[0]));
//        };
//    }
}
