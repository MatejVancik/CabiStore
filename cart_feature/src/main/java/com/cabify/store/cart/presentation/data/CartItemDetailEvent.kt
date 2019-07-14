package com.cabify.store.cart.presentation.data

sealed class CartItemDetailEvent

object DismissDetail: CartItemDetailEvent()

object DeleteFailed: CartItemDetailEvent()

object UpdateFailed: CartItemDetailEvent()