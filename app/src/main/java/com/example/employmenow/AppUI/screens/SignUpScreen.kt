package com.example.employmenow.AppUI.screens

import android.view.MotionEvent
import androidx.compose.foundation.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.input.pointer.pointerInteropFilter
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.sp
import com.example.employmenow.R
import com.example.employmenow.ui.theme.EmployMeNowTheme
import com.google.accompanist.pager.*



@Preview
@Composable
fun HorizontPagerIndicator(

) {
    val pageCount = 3
    val activePage = 1
    Row( horizontalArrangement = Arrangement.spacedBy(20.dp), verticalAlignment = Alignment.CenterVertically, modifier = Modifier.width(75.dp)) {
        repeat(pageCount) { page ->
            val color = if (page == activePage) Color(0xFF5BEE58) else Color(0xFF595959)
            val width = if (page == activePage) 15.dp else 10.dp
            val height = if(page == activePage) 15.dp else 10.dp
            Box(
                modifier = Modifier
                    .size(width, height)
                    .background(color = color, shape = CircleShape)
            )
        }
    }
}
@OptIn(ExperimentalFoundationApi::class, ExperimentalPagerApi::class)
@Composable
fun SignUpScreen(navController: NavController) {
    val screenHeight = LocalConfiguration.current.screenHeightDp.dp
    val pages = listOf(
        Pages.First,
        Pages.Second,
        Pages.Third,
    )
    val pagerState = rememberPagerState()
    EmployMeNowTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.padding(top = screenHeight * 0.1f))
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "efsdfs",
                modifier = Modifier.size(80.dp, 105.dp)
            )
            Spacer(modifier = Modifier.padding(top = screenHeight * 0.1f))
            Text(
                text = "Employ me Now",
                fontWeight = FontWeight.W600,
                fontSize = 36.sp,
                lineHeight = 16.sp,
                letterSpacing = 0.4.sp,
                textAlign = TextAlign.Center,
                color = Color(0xFF5BEE58),
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.padding(top = screenHeight * 0.07f))
            HorizontalPager(
                count = 3,
                state = pagerState
            ) { position ->
                Pagers(Pages = pages[position])
            }
            CustomHorizontalPagerIndicator(pagerState = pagerState)
            Spacer(modifier = Modifier.padding(top = screenHeight * 0.08f))
            GoogleButton(navController)
        }
    }
}
@OptIn(ExperimentalPagerApi::class)
@Composable
fun CustomHorizontalPagerIndicator(
    pagerState: PagerState
) {
    val pageCount = pagerState.pageCount
    val activePage = pagerState.currentPage
    Row( horizontalArrangement = Arrangement.spacedBy(20.dp), verticalAlignment = Alignment.CenterVertically ,modifier = Modifier.width(75.dp)) {
        repeat(pageCount) { page ->
            val color = if (page == activePage) Color(0xFF5BEE58) else Color(0xFF595959)
            val width = if (page == activePage) 15.dp else 10.dp
            val height = if(page == activePage) 15.dp else 10.dp
            Box(
                modifier = Modifier
                    .size(width, height)
                    .background(color = color, shape = CircleShape)
            )
        }
    }
}
@OptIn(ExperimentalPagerApi::class)
@Composable
fun Pagers(Pages: Pages) {
        val screenHeight = LocalConfiguration.current.screenHeightDp.dp
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = Pages.image),
                contentDescription = "efsdfs",
                modifier = Modifier.size(290.dp, 190.dp)
            )
            Spacer(modifier = Modifier.padding(top = screenHeight * 0.05f))
            Text(
                text = Pages.slogan,
                fontWeight = FontWeight.W600,
                fontSize = 24.sp,
                lineHeight = 16.sp,
                letterSpacing = 0.4.sp,
                color = Color(0xFFFFFFFF),
            )
            Spacer(modifier = Modifier.padding(top = screenHeight * 0.07f))
        }
}
@OptIn(ExperimentalComposeUiApi::class, ExperimentalPagerApi::class)
@Composable
fun GoogleButton(navController: NavController) {
    var isButtonPressed by remember { mutableStateOf(false) }
    Button(
        modifier = Modifier
            .size(width = 393.dp, height = 45.dp)
            .padding(start = 10.dp, end = 10.dp)
            .border(
                1.dp, Color(0xFF8F8F8F),
                shape = RoundedCornerShape(10.dp)
            ),
        onClick = {
            navController.navigate(
                route = Screen.MainScreen.route,
                builder = {
                    navController.popBackStack(Screen.SplashScreen.route, inclusive = true)
                }
            )
        },
        colors =  ButtonDefaults.buttonColors(Color(0xFF1E1E1E))
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Image(painter = painterResource(id = R.drawable.icons8_google), contentDescription = "")
            Text(
                text = "Sign-up with Google",
                fontWeight = FontWeight.W600,
                fontSize = 20.sp,
                lineHeight = 16.sp,
                letterSpacing = 0.4.sp,
                color = Color(0xFFFFFFFF))
        }

    }


}