//package com.opinai.account.infrastructure;
//
//import javax.persistence.*;
//
//@Entity(name = "User")
//@Table(name = "Users")
//public class UserJpaModel {
//
//    @Id
//    @Column(name = "id", nullable = false)
//    private String id;
//
//    @Column(name = "name", nullable = false)
//    private String name;
//
////    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
////    private Set<String> reviews;
//
//    public UserJpaModel() {
//    }
//
//    public String getId() {
//        return id;
//    }
//
//    public void setId(String id) {
//        this.id = id;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
////    public Set<String> getReviews() {
////        return reviews;
////    }
////
////    public void setReviews(Set<String> reviews) {
////        this.reviews = reviews;
////    }
//}
