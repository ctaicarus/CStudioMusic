package com.example.mystylemusic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity  {
    TextView txtTitle,txtTimeSong,txtTimeTotal;
    SeekBar skSong;
    Button btnPrev,btnPlay,btnStop,btnNext,btnLoop,btnLyric;
    ArrayList<Song> arraySong;
    ArrayList<String> baihat;
    int position = 0; // biến chạy bài hát
    ImageView hinh;
    static MediaPlayer mediaPlayer;
    ListView lv;
    Animation animation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        AnhXa();
        AddSong();
        ActionLoop();
        khoitao();
        touchLv();
        animation = AnimationUtils.loadAnimation(this,R.anim.disc_rotate); // chuyền vào animation
        setTimeTotal();
        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { // btnPlay.setOnclickListener(new OnClick);
                if(!mediaPlayer.isPlaying()) {
                    khoitao();
                    setTimeTotal();
                    hinh.startAnimation(animation);
                    updateTimeSong();
                    mediaPlayer.start();
                }
            }

        });
        btnLoop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.stop();
                khoitao();
                ActionLoop();
                setTimeTotal();
                hinh.startAnimation(animation);
                mediaPlayer.start();
            }
        });
        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mediaPlayer.isPlaying()) {
                    mediaPlayer.pause();
                    hinh.clearAnimation(); // animation đứng im
                }
                else {
                    mediaPlayer.start();
                    hinh.startAnimation(animation);
                }
                setTimeTotal();
            }

        });
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                position++;
                if(position > arraySong.size() - 1) position = 0;
                if(mediaPlayer.isPlaying()) mediaPlayer.stop();
                khoitao();
                mediaPlayer.start();
                setTimeTotal();
                updateTimeSong();
            }
        });
        btnPrev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.pause();
                position--;
                if(position < 0) position = arraySong.size()-1;
                if(mediaPlayer.isPlaying()) mediaPlayer.stop();
                khoitao();
                mediaPlayer.start();
                setTimeTotal();
                updateTimeSong();
            }
        });
        btnLyric.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent lyric = new Intent(MainActivity.this , MainActivityLyric.class);
                lyric.putExtra("namesong" , arraySong.get(position).getTitle());
                startActivity(lyric);
            }
        });

    } // định nghĩa và khai báo các hàm cân dung
    private void AddSong(){
        arraySong = new ArrayList<>();
        arraySong.add(new Song("Cao Tú Anh song 1",R.raw.love));
        arraySong.add(new Song("Cao Tú Anh song 2",R.raw.together));
        arraySong.add(new Song("Anh đã quen với cô đơn",R.raw.anhdaquenvoicodon));
        arraySong.add(new Song("Anh là của em",R.raw.alce));
        arraySong.add(new Song("Baby baby",R.raw.baby));
        arraySong.add(new Song("Bâng khuâng",R.raw.bangkhuang));
        arraySong.add(new Song("Bboom Bboom",R.raw.bboom_bboom));
        arraySong.add(new Song("Believer",R.raw.believer));
        arraySong.add(new Song("boy_with_luv",R.raw.boy_with_luv));
        arraySong.add(new Song("Có chàng trai viết lên cây",R.raw.cochangtraivietlencay));
        arraySong.add(new Song("Đường một chiều",R.raw.duongmotchieu));
        arraySong.add(new Song("Đi để trở về 1",R.raw.di_de_tro_ve_1));
        arraySong.add(new Song("Đi để trở về 2",R.raw.di_de_tro_ve_2));
        arraySong.add(new Song("Đi để trở về 3",R.raw.di_de_tro_ve_3));
        arraySong.add(new Song("Đi để trở về 4",R.raw.di_de_tro_ve_4));
        arraySong.add(new Song("Dont Know What To Do",R.raw.dontknow));
        arraySong.add(new Song("girls like you",R.raw.girlslikeyoump3));
        arraySong.add(new Song("Ddu du du Ddu",R.raw.dudu));
        arraySong.add(new Song("light",R.raw.light));
        arraySong.add(new Song("Thằng điên",R.raw.thang_dien));
        arraySong.add(new Song("Fake love",R.raw.fake_love));
        arraySong.add(new Song("Phía sau một cô gái",R.raw.phiasaumotcogai));
        arraySong.add(new Song("Payphone",R.raw.payphone));
        arraySong.add(new Song("Mashup đi để trở về ",R.raw.mashup_di_de_tro_ve));
        arraySong.add(new Song("Memories",R.raw.memories));
        arraySong.add(new Song("Một phút" , R.raw.motphut));
        arraySong.add(new Song("kill this love",R.raw.kill));
        arraySong.add(new Song("Từ đó",R.raw.tudo));
        arraySong.add(new Song("Tình yêu chậm trẽ",R.raw.tinhyeuchamtre));
        arraySong.add(new Song("Suýt nữa thì",R.raw.suyt_nua_thi));
        arraySong.add(new Song("Sugar",R.raw.sugarmp3));
        arraySong.add(new Song("I do",R.raw.i_do));
        arraySong.add(new Song("We dont talk anymore",R.raw.wedonttake));
        arraySong.add(new Song("See you again",R.raw.seeyou));

        baihat = new ArrayList<>(); // them du lieu vao list view
        baihat.add("Cao Tú Anh song 1");
        baihat.add("Cao Tú Anh song 2");
        baihat.add("Anh đã quen với cô đơn");
        baihat.add("Anh là của em");
        baihat.add("Baby baby");
        baihat.add("Bâng khuâng");
        baihat.add("Bboom Bboom");
        baihat.add("Believer");
        baihat.add("boy_with_luv");
        baihat.add("Có chàng trai viết lên cây");
        baihat.add("Đường một chiều");
        baihat.add("Đi để trở về 1");
        baihat.add("Đi để trở về 2");
        baihat.add("Đi để trở về 3");
        baihat.add("Đi để trở về 4");
        baihat.add("Dont Know What To Do");
        baihat.add("girls like you");
        baihat.add("Ddu du du Ddu");
        baihat.add("light");
        baihat.add("Thằng điên");
        baihat.add("Fake love");
        baihat.add("Phía sau một cô gái");
        baihat.add("Payphone");
        baihat.add("Mashup đi để trở về ");
        baihat.add("Memories");
        baihat.add("Một Phút");
        baihat.add("kill this love");
        baihat.add("Từ đó");
        baihat.add("Tình yêu chậm trẽ");
        baihat.add("Suýt nữa thì");
        baihat.add("Sugar");
        baihat.add("I do");
        baihat.add("We dont talk anymore");
        baihat.add("See you again");

        final ArrayAdapter adapter = new ArrayAdapter(MainActivity.this , android.R.layout.simple_expandable_list_item_1 , baihat); // khai bao adapter
        lv.setAdapter(adapter);


    }// thêm bài hát , thêm dữ liệu vào list view
    private void khoitao(){
        mediaPlayer = MediaPlayer.create(MainActivity.this , arraySong.get(position).getFile()); // chay tai position
        txtTitle.setText(position+" : "+arraySong.get(position).getTitle()); // in ra stt và tên bài hát
    }// khởi tạo bài hát hiện tại
    private void touchLv(){
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int  now, long id) { // tra ve vị trí now
                if(mediaPlayer.isPlaying()) {
                    hinh.startAnimation(animation);
                    mediaPlayer.stop();
                    position = now;
                    khoitao();
                    updateTimeSong();
                    mediaPlayer.start();
                }
                if(!mediaPlayer.isPlaying()){
                    hinh.startAnimation(animation);
                    position = now;
                    khoitao();
                    updateTimeSong();
                    mediaPlayer.start();
                }

            }
        });
    }// trả về vị trí chạm vào list view
    private void AnhXa(){
        txtTimeSong = (TextView) findViewById(R.id.textViewTimeSong);
        txtTimeTotal = (TextView) findViewById(R.id.textViewTimeTotal);
        txtTitle = (TextView) findViewById(R.id.textviewTitle);
        skSong = (SeekBar) findViewById(R.id.seekBarSong);
        btnPrev =(Button) findViewById(R.id.imageBottonPre);
        btnPlay = (Button) findViewById(R.id.imageBottonPlay);
        btnStop = (Button) findViewById(R.id.imageBottonPause);
        btnNext = (Button) findViewById(R.id.imageBottonNext);
        btnLoop = (Button) findViewById(R.id.songLoop);
        hinh = (ImageView) findViewById(R.id.imageViewDisc);
        lv = findViewById(R.id.listview);
        btnLyric = findViewById(R.id.lyric);
    }// tìm id tương ứng của mỗi Button
    private void updateTimeSong(){ // cập nhập bài hát tiếp theo
        final Handler hander = new Handler();
        hander.postDelayed(new Runnable() {
            @Override
            public void run() {
                SimpleDateFormat dinhDangGio = new SimpleDateFormat("mm:ss");
                txtTimeSong.setText(dinhDangGio.format(mediaPlayer.getCurrentPosition()));
                hander.postDelayed(this,500);
                // Kiểm tra bài hát kết thúc
                        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                            @Override
                            public void onCompletion(MediaPlayer mp) {
                                position++;
                                if(position > arraySong.size() - 1) position = 0;
                                if(mediaPlayer.isPlaying()) mediaPlayer.stop();
                                khoitao();
                                mediaPlayer.start();
                                setTimeTotal();
                                updateTimeSong();
                    }
                });
            }
        },100);
    } // cập nhập bài hát mới
    private void setTimeTotal(){
        SimpleDateFormat dinhDangGio = new SimpleDateFormat("mm:ss");
        txtTimeTotal.setText(dinhDangGio.format(mediaPlayer.getDuration()));
        skSong.setMax(mediaPlayer.getDuration());
        skSong.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                mediaPlayer.seekTo(skSong.getProgress());
            }
        });
    } // seekbar
    private void ActionLoop(){
        final Handler hander = new Handler();
        hander.postDelayed(new Runnable() {
            @Override
            public void run() {
                SimpleDateFormat dinhDangGio = new SimpleDateFormat("mm:ss");
                txtTimeSong.setText(dinhDangGio.format(mediaPlayer.getCurrentPosition()));
                hander.postDelayed(this,500);
                // Kiểm tra bài hát kết thúc
                mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        if(mediaPlayer.isPlaying()) mediaPlayer.stop();
                        khoitao();
                        mediaPlayer.start();
                        setTimeTotal();
                        mediaPlayer.start();
                        ActionLoop();
                    }
                });
            }
        },100);
    } // lặp lại bài hát cũ


}





