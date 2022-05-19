package com.app.examplepro;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatImageView;

import com.app.examplepro.services.MyBackgroundService;
import com.app.examplepro.services.MyForegroundService;
import com.bumptech.glide.Glide;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private FloatingActionButton mAddFab, aboutUsFab;
    private TextView aboutUsActionText;
    private Boolean isAllFABsVisible;
    private AppCompatImageView startButton, pauseButton, imageView;
    private MediaPlayer mediaPlayer;
    private AppCompatButton buttonStartServiceBack, buttonStopServiceBack, startServiceFor, stopServiceFor;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (!foregroundServiceRunning()) {
            Intent serviceIntent = new Intent(this,
                    MyForegroundService.class);
            startForegroundService(serviceIntent);
        }

        Intent serviceIntent = new Intent(this, MyForegroundService.class);
        startForegroundService(serviceIntent);

        buttonStartServiceBack = findViewById(R.id.start_service_back);
        buttonStartServiceBack.setOnClickListener(this);

        buttonStopServiceBack = findViewById(R.id.stop_service_back);
        buttonStopServiceBack.setOnClickListener(this);

        startServiceFor = findViewById(R.id.start_service_for);
        startServiceFor.setOnClickListener(this);

        stopServiceFor = findViewById(R.id.stop_service_for);
        stopServiceFor.setOnClickListener(this);

        mAddFab = findViewById(R.id.add_fab);
        aboutUsFab = findViewById(R.id.about_us);
        aboutUsActionText = findViewById(R.id.about_us_textview);

        aboutUsFab.setVisibility(View.GONE);
        aboutUsActionText.setVisibility(View.GONE);
        isAllFABsVisible = false;

        mAddFab.setOnClickListener(
                view -> {
                    if (!isAllFABsVisible) {

                        aboutUsFab.show();
                        aboutUsActionText.setVisibility(View.VISIBLE);

                    } else {
                        aboutUsFab.hide();
                        aboutUsActionText.setVisibility(View.GONE);

                        isAllFABsVisible = false;
                    }
                });
        aboutUsFab.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, AboutUsActivity.class)));


        imageView = findViewById(R.id.image);
        mediaPlayer = MediaPlayer.create(this, R.raw.buray);

        startButton = findViewById(R.id.start);
        Glide.with(this).load("https://cdn0.iconfinder.com/data/icons/computer-process-outline/64/start_new_beginning_go-512.png").
                placeholder(R.drawable.ic_launcher_background).error(R.drawable.broken_image).into(startButton);
        startButton.setOnClickListener(this);

        pauseButton = findViewById(R.id.pause);
        Glide.with(this).load("https://cdn-icons-png.flaticon.com/512/1878/1878931.png").
                placeholder(R.drawable.ic_launcher_background).error(R.drawable.broken_image).into(pauseButton);
        pauseButton.setOnClickListener(this);

        Calendar calendar = Calendar.getInstance();
        int timeOfDay = calendar.get(Calendar.HOUR_OF_DAY);

        if (timeOfDay == 7) {
            Toast.makeText(this, "Good Morning\nLet's start the day!", Toast.LENGTH_LONG).show();
            Glide.with(this).load("https://musiceto.com/wp-content/uploads/2022/01/lwpgcg7pd08k.jpeg").
                    placeholder(R.drawable.ic_launcher_background).error(R.drawable.broken_image).into(imageView);
            mediaPlayer.start();
        } else if (timeOfDay >= 7 && timeOfDay < 8) {
            Toast.makeText(this, "It's time for breakfast!", Toast.LENGTH_LONG).show();
            Glide.with(this).load("http://t3.gstatic.com/licensed-image?q=tbn:ANd9GcTDLntfoxLw0CTpjgsjdayzgPSnpeU6VAMgjvNqXkRkISVx20YYYujuLf5wtPsdUFtnjzorOIuOeQ3CTwMPJrE").
                    placeholder(R.drawable.ic_launcher_background).error(R.drawable.broken_image).into(imageView);

        } else if (timeOfDay >= 11 && timeOfDay < 12) {
            Toast.makeText(this, "It's time for lunch!", Toast.LENGTH_LONG).show();
            Glide.with(this).load("http://t0.gstatic.com/licensed-image?q=tbn:ANd9GcSV1DnLClpctBRWILcGwq7qbRe-MbFi2Vs1VwRkvhp6XKXxXHOacaZQromCQszFQ1ONbZmw_hkcor-e0aObcnQ").
                    placeholder(R.drawable.ic_launcher_background).error(R.drawable.broken_image).into(imageView);
        } else if (timeOfDay >= 12 & timeOfDay < 13) {
            Toast.makeText(this, "It's time for School!", Toast.LENGTH_LONG).show();
            Glide.with(this).load("https://showmeinstitute.org/wp-content/uploads/2019/12/shutterstock_309241295.jpg").
                    placeholder(R.drawable.ic_launcher_background).error(R.drawable.broken_image).into(imageView);

        } else if (timeOfDay >= 15 && timeOfDay < 17) {
            Toast.makeText(this, "It's time to do homework", Toast.LENGTH_LONG).show();
            Glide.with(this).load("https://learnenglishfunway.com/wp-content/uploads/2020/12/Homework.jpeg").
                    placeholder(R.drawable.ic_launcher_background).error(R.drawable.broken_image).into(imageView);

        } else if (timeOfDay >= 17 && timeOfDay < 19) {
            Toast.makeText(this, "It's time to watch Tv", Toast.LENGTH_LONG).show();
            Glide.with(this).load("data:image/jpeg;base64,/9j/4AAQSkZJRgABAQAAAQABAAD/2wCEAAoHCBQVFBcVFBUXGBcXGRoaGRoXFxkYGhkXGhkYGhoXGhkaICwjGhwoHRoZJDUkKC0vMjIyGSM4PTgxPCwxMi8BCwsLDw4PHRERHTEoIigxMTExMTExMzExMTExMTExMTExMS8xMTExMTExMTExMTExMTExMTExMTExMTExMTExMf/AABEIALcBEwMBIgACEQEDEQH/xAAcAAABBQEBAQAAAAAAAAAAAAAGAgMEBQcAAQj/xABDEAACAQIEAwUFBQUHBAIDAAABAhEAAwQSITEFQVEGEyJhcTKBkaGxByNCwfBSYnLR4RQVM0NTovEWgpLCJGMXg5P/xAAaAQACAwEBAAAAAAAAAAAAAAADBAABAgUG/8QALREAAgIBAwQBBAAGAwAAAAAAAAECEQMSITEEE0FRIhRhcYEyUpGhwfEFFTP/2gAMAwEAAhEDEQA/ACXt7w7LbZ0HsMt1Y5QYuD4SffQbxUd3ibV4ezcUT61rXH8MLlsqeauPiNqyniFkvgv37D/IGjxdxFJLTJog8VTLdbowDD86k9mDGKT+JvmP6UjiTC5as3R/CffSeAmMRaP735Gleoj8ZL7DvTSuUX9zSmpBFONSGFedZ20IpDUs0hqGzR5SGFLpLVg0hA3p9aYG9SFokeCpDy07bptadQUWKAyFiupQFM/eMTkQEDmTR445SdIDOcYK2PrXjNTLi6u6qKjubnIrRfp8noCupxXyTCa9Vq7A8OuOmZnAnYATSrnC7gB+8GnlU+ky+v7m+/j9nBq9DVWHD4gzDL8KEOKdpcXaud34DHPWp9NkXgjywXk0UNSgaze32kxbbMg9xq74TisTcuBGuCMpJhecgD86uGOUnRl5I1YWE100G2sVinvMgu+BZk5envqG/GLnetb78wDG39aJ2ZatOxiWeEYqTsPq8NVfBLBuH7y4zD1ipPGcELQS4jMPGARMgg6c6vJ0soxcm1srKx9RDI0le5Jmvar/AO11JS5IrnLImOODQ/XhryaVW7Mgl9orxg2HVlH+4VX/AGUpC3281HwE/nTv2n3Iw6DrcHyBP5Uv7Mly4W63Vz8lApiC+H7KZa4oku0da6q9VuHWTrXVXbfsJZofEBoh6OPgQR+dZ7jsB3eKvWW9i8pK+p1/XpWh8RH3Z8ip+DA0K9ssKSwup7VtVcegJkfD613Mb3o4OZbWZ3w9CbN6wfatMY91I4M33to/vipnEWFrGJdH+HiE92bmKi4O3kxKr/8AYI9Cax1EfizXSy+S/KNOIpDCnGptjXmmegQkrSGFLzUhjQ2bQmksK7NXjPWDQ2TrT6mojnWpCNRYrYzImJT61GtmpKCmMaATY7NSeGuIb1P1qI+1JwFyCwp7p1UhLqX8CyxADVSX7eUmDUq7iKouJ4yOdMudnOaXJZWOMG2pHwqJhsVcuvJaFn41SLdzVYW7gUaGtRnsVdchXYuLlNZV2zwxS/nOzUb2OKQIK71XdtcB3mHzgarrWnugyeqNgjgFmDRx2bUKt2634Vge4En8qA+BklZ/Zo2S2UsJbOj3W28jH5D50vCLjJsNykhzhdju7T3W3aT8aAcXgi1zMNCTPxNaRxpwlpLY5j5UMHDa0xix2tT8i3UZN9K8Fz2SNwaEyKtu1mLIt21PN1+WtR+zzACOdMds3/wh+/8Aka31KrDL8Mvo/wD0j+UN2bkgVb2ToKosKdBV9a2rymPk9HlJCmlU2ppZNNxFWZ99q1zwWl/eJ+Rp/sFxG0uDNsuBcLOcvM6/1FVf2q3PvLS9FY/Sodq3bt4BLniW5BIYeZpyFaFd/opK3RodmyAo05V1ZRie0V4MYxRjSPCOg/drq32ZezR9EYpZRx1VvpVNj2GayW9l1KH5RV9FD/FLc2LfVWj5H+VdGLo4042gD7Q8KPd3bX47Ld5a80OsD9cqpLF4G5Yu9SoPqDFaFxqGtDEfjtaOOqGA0/Ws84lh+6uMq+wxF22fUyQKLlWqAvhejJRpd66qrmJprDXBc9moeNfNYRvJT9KusEg0IHKvPY8MZ3Z6GU3GqIbWiN6QU86k4s+KqDjXaKxhpDksw3VdSOmYnRffQ+zctMVYTuUrbLMoK8KCga59oJJ8FkAcizE/QUye292ZKLHQBhPoaJ9FP0Y+ph7DXFnKpIrsNckUPYbtRbveCGRjHtaiausG2gocscoOpIIpxkrTLmwalpULDGpi0XEgWQcfaq84pbbNmMaVPagjtmrZ1joafxbOzn9U6gy9bidsz4hVXicO1yWnShXD3TI1rQOGqGtT5UeMFZy9TZE4Tw3OPSpGJ4dk3qVwq6EYjrUjiVxSKjgroK6cLKC9iLdrKWOx2pXGOMrctMqCQRFCnaTHq7ss+zTeDuXkAtuhGcaT0/Rqrp7BcSejcv8AsJwnNJYc9P51cLijexndkCLRAHWSJ+hFTezmNtWcO7MQGUGfhNN9h8EWL37g8Vxi5n946D3DSi48nbdtW3t+DdauHstyR2nwGXK4PKKDsTjMnPajTtpj1VAsjSsjxuNljzokp6YoWyJamwz4RxRZkGmu1PGlL2xILIJgnrpJjWADPw60K4G8bal/+NeZ9KH+K4o57neeIjYxMsSSInYaf7fSgzm8kHFrkN06cZKf9A3XtGUuZM6EaEtljINM0CTmmVj31ZXO01xJYMrIp/EhMiJgMgGux2MTuazHD4rLbBYSM/4gYaAdgPTrypi1xJxDKSq6qQGOojSZOvL3z1pRdHBbJHQfUye7NYw/bgs/+GmXeGui2YjZcy6nfoNRtRjhMQtxEuIZV1DD0YAieh12r5+biRZVDFs0CSDBIGxnmd/iKk4XjN6yc1m6VZQCNyCJGjBiZB2+NYl0y8bFrKE32o3P/kqOifUj+VWfAMVhbluzhsU4CPbI3y+IRoTy0n4UFcX4m+MPeXABdXQgaAqDsB11J9AauMVg1S7YCiJEn4USNQSizW8t0Gn/AEvwX/UX/wDoK6hZ3EnWvKndXoL2pfzG8ioGKwha2yCJLSPjNVz8TY86jtj3POmXOKOcsUmIHCL2Zk8BV1KtLGPXaq5OwhNpEu3gWtzDKv4eQJO+kVObGPuGIPKp+B4t3lvX2hofUUSGXUqQOfT6WpMGv7M9uw9pzJtyAeq8jV3gLwFtWO2UVW33m5cU81JqZw4TZUeUVy18ckqOm94RshdoOId1ae4PQHp1I/XnyrF8fiGuuWY6SSB+fmT1rRftNxmW2lleZ8XoIJ+ZFZ1itp6mBTuDGoRvyxPPkblXgis8a9dvLWlDFuANfP0pl+VdcP69KM0Dix9MW4IPz+daT2T4h31oHXMpysPMbfKsudoitI+zUBEd2HtNPw0pTqoJxGunk1INLLEb6VOtvVbxLGAhWURNR7vFBatNcP4RSUFUtI1PeNl6z0Pcdsh58gfpQF/+QXJJKnU6bbVZWu1He23caQDvXRxwlHlHN6icZRdEBhBok4dx4W7eVgaC/wC8zm0HOp9/EMyiaOjnqGwVYfioYzU25xFSIJ5UBYbFEGrfDv3joswCwBPlOtCk3qCRWxSNhboxRYIzWywOYqcu+07GibifEA7A81EfzPqdPhRD2u4taS2li2FmASRyXp6mgewneMelFmktkClkklRLw5a6629fEdaP8fjGwttEt6MR8qFeyWHBul+SU9xjiJu3mg6L4R+dCh88n4GZPt4Puyl7UY644lmmhm1bLagTRRxLDZ0NU+AwrQdSDtp9PTrRcq3sUxy1tJkPFh3SVbKqyOo0Go56EjqYnWJqlxFtmBk7gbyByYGSNdOfnVg2RXh/FuSNhvmDaRJOh05EelK4mEyucu4R11PsqwQwQBpD7eVSKpHQZUuCqm2ZPicKP2dsx+Sn41HuABMmmZWP0A/XpT+JhipXUFc//cAqkH3qfjUO+wZ2I5xHwFQqx5W9kbwB8xNMhiGhvT3dPnS7BgNO+n/HzrwrJUddSfXT+tZ8mvBPwqump8QTccyuhDD00+NGXEbwe7aYGR3ZIPqBrQZZvHN6AD3EET8Y+FW2Gvy6fu2yPKQRS8027G8Mq2JrOa6mGeuoQzZreauJppTS60ARzVDw1w278crgn/uG9TYqDxO2cocboZ93OpCWmVlTjqjROxFr7wN5EVI4RcC2ix2TMT7qasuHQMOk00XyYW7P7wM+dVKDWS/ZIyuFGZ9tsYblwEnWNfIt4iPdND2KbbyUn3mKlcVuF1zHdmP1/wCKg41oZvKB9aeithKTt2RkMgfxfr612IafiaTZ2HqTSbp1rRkk4PDm66oN2MD4b1svZngAS2jM4VCoygbx1PnWQcDeMRa/ij4qRX0bgsGtvCohAJVBP8USfnSuaDlL7JWN4pqMPu3QPcT4bmCpabQbk0KdrcPcS0bQM5gdaPw1DXahfEk0jCdzTHJR+DTMYfht0fhqRhrd8DIF0O9G+Nw5XUGm8HbDGJE08uobFfpYg2uEuKJIrnxVyIAJoyfAnYxTlvha7AD4Cp3WT6aIA2sRdnVKnJxG4Pwmr7jLJh1zOgIqmHaDDROSr1ylvQPsQjs2NpxIs03A3rrS34nkJ7sGCOc04nHcKfw71NGJwxJGmgneo5y/lK+nxPyRuFdqLtpXQJOYHWmcNxe4u+smT76sEuYU5SI8W2vvpl8ZgwSCwBG+1YUpRdpG5YMc0lJobu9pnWAV3pVjjB1GSZ008/Q16HwbkAMJO21TbHC0QgiZBkeUaipPLNrczHo8V7FDjAytlKzmaCW1lYWH/emQeUa+VOY61FgKTDB2XaMpgTl6gwDG2+xXW97S4Yo9u7GaQj5ZEEOVzMCN9C2nIx0FVd3Dm7bfx6hmkRsAdCB5nY04mmrQtKLTpgqbkLB5af8AlE/MfMU1ZtS2piRoeXl+vKn8YqhiitIzQXIgEHUmNxED50xdZTljTSfeWaayyHBjMHTrz1GnP30qwII+A9dP5Ull8IM6kn4GB/OkM5nf9dazRqyVkKgMdQ2nx/Qq64XhnzImQ5ntgrpE5iTv6AVSYds7qm86e8gAGtks4XDpcQlTmtQo3/CMo5xtQpyjFfJ0M4oye8QO/wCnsV/pn4iurT2xyDlXUvrh7Gal6I60rNSArDdWHqCK8BrTBIdWvSsiKVbwzkTEDq2n1pGIxNi0PHdXN0XWtRxTlwinkiuWNcK+7ZrZ6yvoaj9tMUtrCtGmbT3tpPwk+6pNlVu5bqnwgkDkTG9Bn2j4zM1q0DoX19Bp+vWi03SfINtJNrgEb4k20PISfU+I/ryqqxzyzHqasb1yXd+nhHv3+U1U338RimEKs4tAqOzzXGSYEk+WtWXBOHO95SyHKpBcEQY9DvUbS5ZqGOU3UU3+CLhr5R0ca5WVh55SDB8tK37g/Hlukss93dQMs8iRqP10rEb3BL2Ynu8odmyjfLBlQxGgBmAfKi77NuKDu2S40C20ieja/Wl88nouDGMWPTKpqn9zUBQ32tQnJFKxHa/CKYNz4An6VQ8b7W2rsC0CQNyRofdv8q5+LHPVdDs5xqmyBjbpAyzVXZFxW8utNcR4vA8SaH8Q1H9KiYfjqgQZphQkt6BuceGw4wC3LqNl17tQW8hUZLrhoq5+zoC7ZxLgHIcqT5iSfqKRxbBC0jXFMqoJM7gD61mclCSUvISHzTa8A92vuL3Q7wT1oKF3CbZW/wB1EXFuJ4e6oDPpzpjDcOwgUXB4hrGhIMbnTz0pjGtqdiuV27TRWYbCYa4cqK5J6TVo/ZdAM7M4UiJzg+40scTIJC2lgiJIG3XfSoWJ4szHIx8IM6aa85+Xwrb9Jg1XLSPbPALbareYZTpJGh8gabvdmk3N3fmYpxbtowSZ6Qx3G+nWkYi0tyADA0907HzFZU35NOMfCQ3geCIt1PvAfENK0Q4UdaCcD2aa3dR8ymDO9G7E0LNK2qYfBGk7VEPtUgGFsXNzbdkOn+Wx1GkTo3xAoKusA4UsRlI8Mn4E8xLN8KP8bgbmKsC1aCZkLM4ckTmMKV6xpp/OhLtZhGs3nOSGa0hnmXBQn11B15ZRRenk+AXVRVJrkH8cispIgHMSRsI8Pz1+R6VV3tCRERHuaNfmDUi7cLKrky5J/LU+f66VHvMSZ56z68/fTAkN5udeoaQBTlm0zGFBJ6D6+VUyBJ2GwHfYpFOwIZz+6pkCfMiPfWtPZXMZ6ms8+zYnvXVY8IkkcyfPoIo7S+S59TXL61t7ff8AwdbpI1Eeu2wSda6mDdrq51yHKC7H9qsNa0zZj5bfE0J8U7d5pFtQo5aeU0CXMUXJ15/+01DYz+vI/wA69L3Ev4UefWN+WXuO4/eue07EdJqvR2uXFE7kT5DSTUJW1P661ZcITxAxJkBRy8yep6Vhzb5CRglwaJhiLeGRhtByjbQeEe6ZrI+NYzvcXEyF0/M1oHaDiXdWwoOqwnox1Pv1aspwT5rhbrP0NVBW22Xklskh3EvCKvXM7e8+EfACqlzJqZiLkk+gHwFM4K3muAedFbpWBjHVJIMOzPDlVM0DMRJJ+npRBYwIOVo11g7aHUz5U3gsGRbUkDLmAbrOkCPeKvcX4EgDMx2XbMfM8lG59K5eRuTbPW4VDHCMY+CDcdEEkHXRdNWPkDy86COOXUDt3KZA3txtPM6fo0RcQuFAzM2a43PkPJRyAoa4goyLHtHf0HXyovS4Vuzm/wDKZ+Ifsg2IOp5NHnsf6fCpWdBPIjp9arsOR3b9QVIPxBpq7eIyP7j7v6Gmq8HIUt7LJcauqkAz8/Wkf3Yrapt06VUG6QavOz2J1g7flQsqlCLcQ+JxnJRkaX9kuICW7uEZddbqnqNFIPvj413bzMuHuqn4yF9ATrUj7PbAFy9c/ZQL/wCRJ/8AUV3aY95bdQJJIgeciks07jCT5sdw46nOK4ox8cEY7VZ4lO6tLbgFkAn+JhMRzIB+LCi7+5LyJ3hTQAnlpAoFxdt3DuSSz3GgbZUQnM56Sfypzp5znbkJdTCEKUSHeaJlv4td26Co6tm9owIMepEUu6sxAgDT1O5PvJpF8hRpryHSRv68vjR6F7ONshYB10MeQB2+NLw2KMKOeo9do/OmFuQdTLHT0ppDDbazVtWtyKVO0W3f3C3huMfyMSNuVPf27FD/ADDTPBmm8oXYsun/AHZf5UYX8AP2RSebNHG0mh3FilkVplf2S4/etYu0bhzI7C2w8nIAb3GDWoce4ct1WVhvpy89vcTWeWeDuwNy3bJ7oqSQRpr4dz1FaxeQOvkdavHk1q0qKnj0um7MM4v2cv2GdkAdASQNMwn9078vhQu7EE/TaPdyre+I4MAEb+o/Osk4pwpjiLgtiADroTqQD+dHhmTtMBPp2qcfIOR5UVcHwZ/stwZSGc5c3VYBMfSl8L7Pyc1zWDp5+6i2zwxntkoQCp0Q7t6E6Cl+o6jbTDkY6fptPynsis7E8Pay1xpOoq+tXmBJ9akcP4TiLaklBr0ZWPwUmkFGBMikM2SUktXI/ijBbR4Gf7Q3lXUrXpXtAtBqBzF8BvWwXUC6g/FbkwBPtLuu/pVaBz/XKpOH7Qd0VbOZVeTCdYPLnB9dKn3+0OCvf41l1uaDvbJTMSebL7L6jc6+dekljXg85HI1yU4/X699EvZyyEm8+gUEr156x8Kou4tsfur6NvAebTmIMQ3hmOjVNd2s4dgzFWubAj2QPx9CenLnrWFB3ujetVsV/HuIG4entNE6jwkSfOdKocCgQHrH5H+dOXDltsx5sEAO5A8RH86hpdi2XPtOSB+fuoijQJu2cUzNlHMhfiY/XpVjw7hj2sQEuiNiDyYTuDXvZjh5v31VGhkyus/iykSPMa6+tbFjOAWr6xcXQezBgjzBG1Byzf8AChvpFFNTl4ZSYlP/AIYuIJMudOqk/wBKRhMaL4e7+H2UHkIlj6tOnlVhxTDLZspZtkhUncyfExJk896HeAMtu0wiYZxr/EdqRZ2MedRuVc2ys4ziQC0akDrt5wKHeIPlUDm25/IVK4pfzXgACRIJiTJB0SPOqXG4gl2zbyd+Wu1P4lUUjidVkeTI5MVYU5HI2GUe/wDQqMtyQVOx1HkfKpVpvAVB31+FQisHWiULiGWI/W1W3AHPeoOp/InWqstIPlRD2bwWZReWcy3MoHI6An60PK6g7C4U3NUa92MITC3X5EhR5wD/ADFN4FM+JtqdVOYn3Cn8LFvCW0XmCx6yxkg+le9nDN8noh+ZWuZJXljD0daO2OU/f+iX2xQjBYjJEi03wG4+E1ht2+Tmt6TAYn9osQSB1GrfKvobjGBN2xdtr7Vy26ierKQPnXzc1q4pIeFZcyQfaDI2Vhp0j5V1YRo5GR2IsrKMZmNAJ5mTPvH1qNiVK5BMmAdNhIB/P5V41yNjE6/L/ijnsx9nOIvhbmKmxbgQpH3rKBpCnRBECWE+VboC2kANxI0HxqdguD4m4veW7F25bUxmS2zLI5aDXWtnt9g+GgLmw5PIk3bxM+cOBHuq7wuHFphYRUFru2hQIVVALDKBzmd+pq9DoH3lfBl/2WcBt3b903QM1pVhWkMGZj4sp2gLHvrTcR2csRoDuB4Trr5VBw9yL6xCkqA5gZ2AZsqltyusgbammuN4S/33e27jKqrGVTBPWT+VKyxRyO5DMeqlCNQCFODWlsd0hIDEkkHUt1ny5elMIz2lVbgzBQBnUcgIll5e6actYtra21dGysinPvDlZYMANB0PxjSnbmIkLEENGu4IPOmexBxpbC31U4y1Peyq4hdRkzIQQehmg+/bGYwI9KLOJ8GtuS1tjac75fZJ/eXY+og0NYnh963MrnHVNdPTeudnwZIO62Ot0vWY5qrp+mQQNYr3G3CgS4NQhIKzAOaIJ/5500uIEwdDzB0NOvfDKVMEHcHmOh8qUjcZqSHpqM8bjfI/g+0fik7fTy8jRbbZMRaDHf8ACT9D5VnuH7Pm60WWuKfUMg/iLjQe+jKwy2LNuyrBigAZhpmc+00cgTOnSm3jjki2cyU5YZJWrGmwxH4TXUq5xW7JjUcvFFdSn0UvYx/2K9AjhuEYUy3dFyQpzXI1JAIAQAAaSNBqRvrTn91Ycnw2Uu7wtrMskFZJnTYjQHpSL3FV30Y+IhToAwUFAc3hADedNW+I37pi24RIiLYKD2nAJLQDso0+s16FUjjO2ScRwvBAa4XEA/8A1qzaa65W1j+Yry3YweXIt8oTqLd5Tbk/w3IB9R1NPC53YJfENmVVHgcwJAPtsTr4DsRufSm8Tj7ZDKxR1mDnGcfiliTIY+A8jW7SM0wS7SW2DKqW2VEB1GoLEyWDDQiqC7cmByA0oyxtjDLJRlXQ62mI5n/LbQ7R4T10qlxGGVjP3bzHiQmQD+Jl3HKd96GwivyF32bElfHa8ILNbuiJn2WU8xzg+tapgYYVgnBuMXcKZttnt6zbJ+YI2NaXgO0lu7g3e26BmyIwZ8rIHdUflOYZtOpI1peUN7GoZFpSH+0eFu37btYnKZyvnyFo5ppqs6AmJ5aa0HcPuutlg4KvLZgRBDEzBFbGirkVVAAAAAGwA0AFUPG+zKXvaGvIjRvjS88TS2GoZr5MRxuIEMJOZmGo3AE+z561DxmFAVbimQxOh3EHQn1o54t9nV0HNauAwZhtD8RQrjOB4y2SHtvl6jVQOsjajwkqSTFZxdttFKrkVMv3gUB261NtYNT4TctqRuDJO0zoPz5jrUl+G4dRD3zM/gVTPLTxH9TzEUZX6AuvYOpbJBMxG2u9F3ZJ3CKJ8OZiBpqdvjvTFvh2E0kmJjNcfQbiStsajwkxvlIbbQzlx1pUHd+FQCxn7tYGyFbcSZlSC0ExDEyRjJByjV0bxTUJXVho/FFt25uXBMAmdwPZBIGw0iTU7spxe195cVs0kLoD4QupkESN/lWbDD4u/lFvD3SurR3ZW3m12aAGJELOmmkkSxvOy3ZriVu4HyIiMYcPcBLr1hM3iHKfTak1ghilrUrf3G59RkyQ0qNI0bF9q7VtVcgsrGMyiQJ5np61mPEeyV/G4y4+EUGxcbvFuOwVENwy69XhgxhQdx1rROEdmhbYtcuZlLZxbCgKDoYMzIkeVEdy/Pl9fL0pnE3N3f6EMkpQXyQKdmew+FwUOR3t8a944HhMf5abIPPU+dXuJvvyIXzIzH56D517cxG+Ubb+tVtzGg/n5U1QnKdi3IuAoWOblqdfTWpPCbGVAzgSFa3J/jcn/wBapMTd8WXYxmU1KHEs9uAYLqW8jctGHHqVg/8AbVx4YKTWpNlVxeAzXLYb7o5XAMCDqGgHaZqbwriAugS2RtomZ90Qah2sQpxbLul0ZD0zFRH+8R76r3wptvpIg/CgtKLNqTYTcT4gs20RzKDU5YE/Wo2IZzbm22V1OY6eFtd4+vOotpu8GvtDn1pdu5Gje6tp7lN2e2eOzK3FyNzMyp9DTn9uB5j48qg4nCg6iq65Y5fry/Op3GtmVpT3RZ3uKId1Vh+8AfrUN+Lovs2rQ/8A1p/KmsIuUZSsjXpNPGyvI/7Kw0nukFjkktrY23Eb1zSSF8tBXgtM+k6AzJkyR76eIQbhz7oFeriCPYtn30GUWEU1wSBYuf6n+wV7TXf3ug+FdQ9LN6olC3ZLFqdbYZVLFcl22ViEhcjkQDkHx8qaxfDsagyphbsZYOUBh7aMYysc3stvG42ogHH11I5rPtAD2A25gExOg/ZgU8OP6rMgGRqN4cCRrroQduddKkYt2Ap4fdWWvrcRmy/4loqAcsSVQEEAvzI3J6iotjFKz+G2bhDEv3jKttd22VgqwDcEeWkzppFvtKdpYEEDKwIOpZdVjwiRGvWnl4wtww2RgQDDIp3WQDm0nfn9azoXs1qYB4biLgAwrgSWyWrVq2GUHNme4gz6oTpqQ3XSm8XirZym5btE+I/d29JGYEZ3dRmlI0XdhrqKOL9zCXINzD2X5f4ajcEiCIMGT7jypteF4DPm/s4U+0Cty4OjAqCd9AZjz61HF+GRNeUA2K7P3Li97h1OoPgIIMA8tx5wevOqFuF4pDnSxfU6jS2/wJA1Fa2eD4RvZu3lnUDvVIGhGgK6aHczsPI0m7wEalMV7ntyAf2iwudddgNT6VNBese4Vxt7ItpfYSyjxHTxRqPI0WWeIqwoBxnAsQ0Q9i6ARoXKtpsRKwDGm+uWecVHwD4+y+V7E2jpNp1fL5gBixU7660KWNrgNHInyaNcyHpULEWR5VR28XcJ1VlPmpFS0xL6A0vOCfKGoSa4IHFOC4e7PeWkJ/aiGHow1oSxH2eozzbuso6OM2v8Qgn3g+tGeLxYzEDUjeOVRn4goNJ3kg/ixlwhNbopMF9m9ga3brMNNEXKdP3iT9KK+F8CwmG/wbKKf2iMz/8Ak0mm7eNEU4mK1rUsk58sqOKEeEXKiaeSBVUuOAG9V/E+PKikztUUEiNtl9iOIKvOoC8VUsEn2vZ8z+z7+VZXxLjWIuMHAuMCTkUaLA0mVPjBzaggRoAdCTAe9cYmb2UyPAjAsDMFdiohtyWkZl0gCmoYpwakmJ5ckJxcWjWsfjChzLuN1n216fxDkf61R4ji1piTmyzz2BHn0PUGhm/jrjZVuXfEVBlnUBdIJuEDUhgQ2XbVo0K1X32HiOgZZzBiHJOoYhMsgqASwgyssIKimnKznrAwsxHFVyj7xSVMoQw/8T0B/OpvCcYjm2EMzcZ/RO7Jaem2X1oDEeElSBrpK3HBX8MrBG86qJXMRqni9uKwuQoPe7AW5bNmEySGkZlIjTxZoMFSTIzplS6XUuQkfEwSQdUMg9CNRRhxBUZiRvOorIg4BkAyPEQpIK6+EmV8Q0nbmFME1NHE8VmP/wAhpJ5lAuntFjcErB3gaQ2gAk3cWqZXYknaNFt2eYNSe6kQd6zmz2ixS/jVo3DIFadIAhxJIK6AT4tM0E1N/wCsb6iciMsgSXK7iRpDaQCfh6VhUtiPFP0G3dkHKfdXiYUHN/A2nmII+QNB9vtpcOr2GGkiXAkazEjUaHXyNPN21KkDuXzDTLK5tQORM/iHxqbFdufoI0wm37315VITC/Gg09t4EdzcAjT2Tpy2bTavT26tg+K3cBk6FTIM6iPWsrYvty9BobaxJP8AzUe5jLa7amhQ9srR1ZLseSGnrPazCFS2S5A3JX3c9/dWZSl6NLGXR4kP2a6q632jwpAIVzP7n9a9oe/oJoBlrxgMJyhibeZFbOc6NlaGXNIY6F3nMVGhWktcjw+EZj4i2ZMkSrLICj2cuuV9VU6Q0MZJIhZZhCLlHiBBtyQsMXEjdWJnnm8fBNlDb+0fGM5ZQcpVZAaRpK85mSpLpRKGKJOuiwFZEYhmbmwQgn20Vo7vfMumlOJjn9kZu8Hi0KsttCouzkAbZiWmFlWI6xCdxKldfCFVZRgugMOpGWIBglFGk7DwJGH1KclcFzkDZDmYZdM4y+IxoNSechslk4YuXMZguupUHxgyEJJYKM7NbJDDR0J0kFy1xRcoYMYBgL4pYBSwDSSFlM6QHBBXyM1NxZHiAiAMniMNlKZgGzACQvMCI/DlIWl528QZnuMJJD95KkBmXOczBokaMDKyACCDdkL5cfAYZ5AGaUeEIAkxm0Ym2ZADTKHeBUuzxWFzH2W0ZyAVlYVoiFMTbPtfinnoJCdwTlWQLmQSzaGDdWT7QU+3AzHZW08F1TmfKNZWFLeDQgfttGR2GUsNFI0KLU1EoLX42SBJIgxENJnQKd0ADjWY9sddJOG4xJORmfNqqhgxEjMASsBdmUyNMo86DDfckkEs+Vs5zC5KFEnxfeP7KmTI/C2morzvpDJmVokh3WHYkyw8WcglwTEABnI2cTNbL0oOLXaORKksBuEB6ZlOmgzdPKOVTbXGlbwhg3QmCDpO5jUgnlWdG8CSWlbZMZRc1nVgVS6SYzLI8GveOu9KOLuKozNcB8ORSJQqTmWA5VW11ACkFbjDlpWr2XRoTX7HO2PFuVLjU68iBqPzpi5YsDk46febjqM8z/zQOmPZWBzA5gdYICGZBBARPDuCJGW7+7Tx4g5JCalMxNxWz5VWScuQAeEjMPFJV16VlqD5ijanNcSYZBEiVdwNhOU+7ZaQUfldHlmUj6E0If3o5gKzhSIdiBdysdXyhA2qkFh4gSgOp1rwcYaWXPlZJjxFXOgBVAC5nUMsjqOVYePH6NrNk9hNicLjCJTu2Hk5/MAUNcTwWPIP3LH+Fkb5Bp+VS7PH2UZzGXQGSAWMZsyhyGGZRmBC7kDnVjh+PwMzghWkozLuNlOZsoyk6TyIOkaVSxwLeabKFOHX1tgOlycqpqjKApDEyuoYQN4GsTTVkpYkrrcWRcZgIA9jLOaFB1QnONChjnRba7RjLM5YE7MTGxOgIBGkgnmvWnv77tvowBklcsrcdSNwVB1/ONNqNaAqLAa5iBlPjYW2JdS6L4m0RWcSAf2Lg8f4jzmmLt7RJJDLlCIyDQJGXMCMpZDAP3fiVn1o3vjClvHasywkFrYBIcaQYGjAk6nWojcLwhbMUykakrce2wO0kBhsGif3o02rOxqmCbXlnUq2ZRIYezrmKoHEBhOZCqRF1xSGYE5JQ6kl5gGQdFNw5Qj+NwQkq7CKLLnZbCx4Lly2rb5WGsGdZEyGj0+VQr/ZJBCpeYTJ1tqNDG5UqWBgaVRKZSG5KkjMogByWMEscpdg8LlLeFlCGO6BrmvKqgRcWydQDGaVUACWyo1wKEGinMrnerK92Ru7rdtmI1yskmI1InUiQddajf8ATGKBMKj/AMFyDzy6kZvCYjXlFVRCM9+VVbhI0ASAApkwDmaB3Z8bqwQlTGted+cwdozyfCyQsQASVPhPiKqyi3qEzCvX4Li1Bmy55AowOk6r4SWKkCInQzTLWr6HObd9XnVyjA7Fc0gZiwzNudRVUXYtrwXaGzLBBBGWQSoBbdsqnK4T/MIO9K2OQOoJOlwB0PRiFMmAzXJGQFcsggVXjFZJC+GVhljKNdSuniKmF3OlLXECDbD+Aka5QDMBc0JrmHj3bxA1KJZMzyCFyrCksS0Zxo6qJIkx3YEKM2+hpywiLz0BAMnLcKklGAGp0i4ZCGOYFQlxI9mVCiT7JAMtnhlt6shOSAzHKRXhv5o1AywIAylmAySVt73NXMsfEKqiWTX11YMFghQCgbNlLAFdcxlxuusCCKReOs3JE6p+FSoaCcuYsBFvoCCdZ3DC3xOci3l/0wSBBlzCWzOQwsgsYIpCELuPalQqtDBgAgkJrIzvBY+IaGoyKkeXsJcnxJBgSDaboP3a6pS8Na548rmdoe0gyjRYVjI0A0NdVfsv9FnibTANmAl1LahcuVRnJAEiYnkJk9SCzddV0MZRrlOYZ8pJmPEoOUt057yQ3ldTCFmPOWLKplrjRGYg+HUfiBysAp1Vh7PUAs1cZScp5AwGXxEkBcpgnpEhhAgCBGXyuqyxSuSRBlz7HjabeziCwOhB5NsxnUsD6LPiKkQFIzyFZlOcqckmYgkRn/D5Ka6uqmRDdzLnkhcw/DqJhfakDeY/EPa6FlpZtszhfbuMojMZ0bMI8YOsD9qPDHJTXV1UyyO7KCoYwpOYllzGRBMHxGPFI29qYnMC7bsEqJkKSBlDHxMBlMZiy7qRqBvHsxHV1Uy0NOSoDkH2VZdAysvVxmWf8NdCCSBrqASxZZQ0WjLkiMpZChDSASoSdQmsmJkcxXV1V4L8jrAqzBZzgMbklZhWgEGCQc6rs2hMjQmEX2AhTACkQ7L4idcon7w7pA6SeRrq6q8lnKM6EgN3a5QyqxOgmCO8aB7DkeEwSRsa8FwsACzQBCKvmZUQCgUZizDeCI2NdXVCzw31BFwqgzQe7UG3IYFlP3aiASFIGaVK9DS8ndqGdT41lHlR7UiT7beIK07QzSK6uqeSDgxJJ7zMzXCwILDvByAJNxt8zKjDLBCgim/7WFnxhyQQQc5KHbQQqZlEkEaQ0V1dURBy8722KFYckDR8i5iA7IBbGxlYJIiKUuOdSGUu1zMPGQmXUlFaWLP4vFmHkDXV1Z8GlyLscVy+wwYsplYYlBJkAsVXMgGjAa5jUy9xO4jZbgGckAeI6NAbKAqxlIKgSfCZ611dU8kvYcbjLAFznLg6k5chEhDIJJkOcp0gqNK9sccIMKQ7ZiDb8QAf2QA3hEMdQeQABrq6p4ZCe/Fyirn2ZMykaaMcoLQDzkkbxseVOf3oYgghiBAGszMSxIjQE/LSurqyaEHiSMJaI8OhBJ16aR6/KkvhrLjM1pCuaJKLMxtprsZryurTMkduD4RyB3cdMpZRrqNjUe92ZsNAVri6bSDpsNxXldVl0hm92UbQrdB5gOmnIbKY6Daolzs3iFO1thGwYgEamI00kz7q6uqiqRXtwW//AKflvb5ada6urqlkpH//2Q==").
                    placeholder(R.drawable.ic_launcher_background).error(R.drawable.broken_image).into(imageView);

        } else if (timeOfDay >= 19 && timeOfDay < 21) {
            Toast.makeText(this, "It's Dinner time", Toast.LENGTH_LONG).show();
            Glide.with(this).load("https://realfood.tesco.com/media/images/1400x919-OneTrayRoastDinner-17b80fd0-8071-474e-84e5-f19eb03da1b5-0-1400x919.jpg").
                    placeholder(R.drawable.ic_launcher_background).error(R.drawable.broken_image).into(imageView);

        } else if (timeOfDay >= 21 && timeOfDay < 23) {
            Toast.makeText(this, "It's for sleep", Toast.LENGTH_LONG).show();
            Glide.with(this).load("https://www.skipprichard.com/wp-content/uploads/2020/01/kate-stone-matheson-uy5t-CJuIK4-unsplash-1080x675.jpg").
                    placeholder(R.drawable.ic_launcher_background).error(R.drawable.broken_image).into(imageView);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.start:
                mediaPlayer.start();
                break;
            case R.id.pause:
                mediaPlayer.pause();
                break;
            case R.id.start_service_back:
                Intent serviceIntent = new Intent(this,
                        MyBackgroundService.class);
                startService(serviceIntent);
                break;
            case R.id.stop_service_back:
                Intent intent = new Intent(this,
                        MyBackgroundService.class);
                stopService(intent);
                break;
            case R.id.start_service_for:
                Intent intentStart = new Intent(this,
                        MyForegroundService.class);
                startService(intentStart);
                break;
            case R.id.stop_service_for:
                Intent intentStop = new Intent(this,
                        MyForegroundService.class);
                stopService(intentStop);
        }
    }

    public boolean foregroundServiceRunning() {
        ActivityManager activityManager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningServiceInfo service : activityManager.getRunningServices(Integer.MAX_VALUE)) {
            if (MyForegroundService.class.getName().equals(service.service.getClassName())) {
                return true;
            }
        }
        return false;
    }

}
