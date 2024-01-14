package com.renderson.testomie

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.renderson.testomie.data.OmieRepository
import com.renderson.testomie.model.Products
import com.renderson.testomie.viewmodel.OmieViewModel
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.*
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule

@ExperimentalCoroutinesApi
class OmieViewModelTest {

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @RelaxedMockK
    lateinit var repository: OmieRepository

    @RelaxedMockK
    private lateinit var viewModel: OmieViewModel

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        Dispatchers.setMain(UnconfinedTestDispatcher())
        viewModel = OmieViewModel(repository)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `getAllSales should update totalSales`() = runTest {
        // Arrange
        val sales = listOf(
            Products(productId = 1, name = "Produto A", amount = "1", unitaryValue = "10"),
            Products(productId = 2, name = "Produto A", amount = "1", unitaryValue = "10")
        )

        coEvery {
            repository.getAllSales()
        } returns sales

        // Act
        viewModel.getAllSales()

        // Assert
        assert(viewModel.totalSales.value == 20.0)
    }

    @Test
    fun `getAllSales should return error update totalSales`() = runTest {
        // Arrange
        val sales = listOf(
            Products(productId = 1, name = "Produto A", amount = "1", unitaryValue = "10")
        )

        coEvery {
            repository.getAllSales()
        } returns sales

        // Act
        viewModel.getAllSales()

        // Assert
        assert(viewModel.totalSales.value != 20.0)
    }

    @Test
    fun `insertSales should call repository insertSale`() = runTest{
        // Arrange
        val sales = listOf(
            Products(productId = 1, name = "Produto A", amount = "1", unitaryValue = "10"),
            Products(productId = 2, name = "Produto A", amount = "1", unitaryValue = "10")
        )

        // Act
        viewModel.insertSales(sales)

        // Assert
        coVerify { repository.insertSale(products = sales) }
        coVerify { repository.insertSale(any()) }
    }
}
