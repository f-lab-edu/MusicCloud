package com.flab.point.domain;

import java.time.LocalDateTime;

/*
 * 포인트 종류
 * 1. 상품 주문 시 1% 적립 포인트 2. 이벤트 포인트
 *
 * 기업 부채로서 작용되는 포인트 이력을 정확하고 상세하게 관리하기 위해 update와 delete가 없는 도메인 모델로 구성
 * -> PointTransaction에서 포인트별 사용 및 차감 상세 관리
 */
public class Point {

    private Long id;
    private Long userId;
    private PointCategory pointCategory;
    private Long amount;
    private PointStatus pointStatus;
    private LocalDateTime registerAt;

    // TODO 추후 주문 상품별, 이벤트별 만료 기간 설정. 현재는 적립시 적립 일자 + 한달로 설정
    private LocalDateTime expireAt;


    public Point() {
    }

    public Point(
            Long userId,
            PointCategory pointCategory,
            Long amount,
            PointStatus pointStatus
    ) {
        this.userId = userId;
        this.pointCategory = pointCategory;
        this.amount = amount;
        this.pointStatus = pointStatus;
        this.registerAt = LocalDateTime.now();
        this.expireAt = calculateExpireDate();
    }

    private LocalDateTime calculateExpireDate() {
        if (this.pointStatus == PointStatus.POINT_EARN) {
            return LocalDateTime.now().plusMonths(1);
        } else {
            return this.expireAt;
        }
    }

    public enum PointCategory {
        ORDER_POINT("주문 포인트"), EVENT_POINT("이벤트 포인트");
        private final String description;

        PointCategory(String description) {
            this.description = description;
        }
    }


}
