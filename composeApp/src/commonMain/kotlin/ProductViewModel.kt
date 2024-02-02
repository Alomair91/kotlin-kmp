import data.ProductRepository
import data.model.Product
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ProductViewModel : ViewModel() {

    private val _products = MutableStateFlow<List<Product>>(listOf())
    val products = _products.asStateFlow()

    private val repository = ProductRepository()

    init {
        viewModelScope.launch {
            repository.getProducts().collect { products ->
                _products.update { it + products }
            }
        }
    }

}