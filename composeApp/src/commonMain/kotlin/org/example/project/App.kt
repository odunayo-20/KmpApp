package org.example.project

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.seiko.imageloader.rememberImagePainter
import data.Product
import org.jetbrains.compose.ui.tooling.preview.Preview

//val scrollState: LazyGridState

@Composable
@Preview
fun App()
{
    MaterialTheme {
        AppContent(homeViewModel = HomeViewModel())
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable

fun AppContent(homeViewModel: HomeViewModel)
{

    val products = homeViewModel.products.collectAsState()

    BoxWithConstraints {
        val scope = this
        val maxWith = scope.maxWidth

        var cols = 2
        var modifier = Modifier.fillMaxWidth()

        if (maxWith > 840.dp)
        {
            cols = 3
            modifier = Modifier.widthIn(max = 1080.dp)
        }

        val scrollState = rememberLazyGridState()


        Column(
                verticalArrangement = Arrangement.Center ,
                horizontalAlignment = Alignment.CenterHorizontally ,
                modifier = Modifier.fillMaxWidth()
        ) {

            LazyVerticalGrid(
                    columns = GridCells.Fixed(cols) ,
                    state = scrollState ,
                    contentPadding = PaddingValues(16.dp)
            ) {

//                val searchQuery = remember { mutableStateOf("") }
//                val isActive = remember { mutableStateOf(false) }
//
//                item(span = { GridItemSpan(cols) }) {
//                    SearchBar(
//                            modifier = Modifier.fillMaxWidth(),
//                            query = searchQuery.value,
//                            onQueryChange = { searchQuery.value = it },
//                            onSearch = { /* handle search here */ },
//                            active = isActive.value,
//                            onActiveChange = { isActive.value = it },
//                            leadingIcon = {
//                                Icon(
//                                        imageVector = Icons.Default.Search,
//                                        contentDescription = "Search"
//                                )
//                            },
//                            placeholder = { Text("Search Products") }
//                    ) {
//                        // Optional content inside SearchBar (e.g., suggestions)
//                    }
//
//                    Spacer(modifier = Modifier.height(16.dp))
//                }

                items(
                        items = products.value ,
                        key = { product -> product.id.toString() }) { product ->
                    Card(
                            shape = RoundedCornerShape(15.dp) ,
                            modifier = Modifier.padding(8.dp).fillMaxWidth()
                    ) {
                        Column(
                                verticalArrangement = Arrangement.Center ,
                                horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            val painter = rememberImagePainter(url = product.image.toString())
                            val image = Image(
                                    painter ,
                                    modifier = Modifier.height(130.dp) ,
                                    contentDescription = product.title
                            )
                            Text(
                                    product.title.toString() ,
                                    maxLines = 2 ,
                                    overflow = TextOverflow.Ellipsis ,
                                    modifier = Modifier.padding(16.dp).heightIn(min = 40.dp)
                            )
                            Text(
                                    product.title.toString() ,
                                    maxLines = 2 ,
                                    overflow = TextOverflow.Ellipsis ,
                                    modifier = Modifier.padding(16.dp).heightIn(min = 40.dp)
                            )
                        }
                    }
                }

            }
        }


    }
}