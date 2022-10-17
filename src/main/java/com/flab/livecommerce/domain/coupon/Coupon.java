package com.flab.livecommerce.domain.coupon;

import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;

/*
 * 상품 카테고리별 쿠폰 도메인
 */

@Getter
public class Coupon {

    private Long id;

    private Long categoryId;

    // 쿠폰 이름
    private String name;

    // 쿠폰 사용 만료 날짜
    private LocalDateTime expirationDate;

    // 최소 상품 주문 금액
    private Integer minPrice;

    // 할인 금액
    private Integer discountPrice;

    // 상태
    private CouponStatus couponStatus;

    public enum CouponStatus {
        AVAILABLE("쿠폰 사용 가능"), USED("쿠폰 사용 완료");
        private final String description;

        CouponStatus(String description) {
            this.description = description;
        }
    }

    @Builder
    public Coupon(
        Long id,
        Long categoryId,
        String name,
        LocalDateTime expirationDate,
        Integer minPrice,
        Integer discountPrice,
        CouponStatus couponStatus
    ) {
        this.id = id;
        this.categoryId = categoryId;
        this.name = name;
        this.expirationDate = expirationDate;
        this.minPrice = minPrice;
        this.discountPrice = discountPrice;
        this.couponStatus = CouponStatus.AVAILABLE;
    }
}
