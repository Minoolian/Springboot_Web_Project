package org.zerock.controller.dto.board;

public class BoardSpecs {
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
