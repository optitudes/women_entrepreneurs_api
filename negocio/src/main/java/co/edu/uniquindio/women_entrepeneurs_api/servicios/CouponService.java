package co.edu.uniquindio.women_entrepeneurs_api.servicios;

import co.edu.uniquindio.women_entrepeneurs_api.entidades.Coupon;

public interface CouponService {
    Coupon registerCoupon(Coupon c) throws Exception;

    Coupon updateCoupon(Coupon c) throws Exception;

    void deleteCoupon(int id) throws Exception;
}
