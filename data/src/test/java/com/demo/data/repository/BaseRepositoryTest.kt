package com.demo.data.repository

import org.junit.Rule
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoRule

abstract class BaseRepositoryTest {

    @get:Rule
    var rule: MockitoRule = MockitoJUnit.rule()
}