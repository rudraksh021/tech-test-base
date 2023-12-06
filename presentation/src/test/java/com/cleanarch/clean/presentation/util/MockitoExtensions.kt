package com.cleanarch.clean.presentation.util

import org.mockito.Mockito

/**
 * Created by Aanal Shah on 05/12/2023
 */

/**
 * Returns Mockito.any() as nullable type to avoid java.lang.IllegalStateException when
 * null is returned.
 */
fun <T> any(): T = Mockito.any()

/**
 * Gives you the ability to mock observes programmatically
 * **/
inline fun <reified T> mock(): T = Mockito.mock(T::class.java)