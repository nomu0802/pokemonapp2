package com.example.pokemonapp2;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.res.ResourcesCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import static androidx.core.content.ContextCompat.startActivity;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.MainViewHolder> {
    private List<pokesorch.RowData> rowDataList;
    private Context context;
    String type1name;

    MainAdapter(Context c,List<pokesorch.RowData> rowDataList) {
        this.rowDataList = rowDataList;
        context = c;
    }

    /**
     * 一行分のデータ
     */
    static class MainViewHolder extends RecyclerView.ViewHolder {
        ImageView hogeImage;
        TextView hogeTitle;
        TextView hogeContents;
        ConstraintLayout lay;
        TextView typewaku;
        TextView typewaku2;
        TextView type2;
        TextView pokeid;

        MainViewHolder(@NonNull View itemView) {
            super(itemView);
            lay = itemView.findViewById(R.id.lay);
            hogeImage = itemView.findViewById(R.id.hoge_image_view);
            hogeTitle = itemView.findViewById(R.id.hoge_title_text_view);
            hogeContents = itemView.findViewById(R.id.hoge_contents_text_view);
            typewaku=itemView.findViewById(R.id.shtypevi);
            typewaku2 = itemView.findViewById(R.id.shtypevi2);
            type2=itemView.findViewById(R.id.typesorch2);
            pokeid = itemView.findViewById(R.id.pokeid);
        }
    }

    /**
     * ViewHolder作るメソッド
     * 最初しか呼ばれない。
     * ここでViewHolderのlayoutファイルをインフレーとして生成したViewHolderをRecyclerViewに返す。
     */
    @NonNull
    @Override
    public MainViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_holder_main, parent, false);
        return new MainViewHolder(view);
    }

    /**
     * ViewHolderとRecyclerViewをバインドする
     * 一行のViewに対して共通でやりたい処理をここで書く。今回はテキストのセットしかしてないけど。
     */

    @Override
    public void onBindViewHolder(@NonNull MainViewHolder holder, int position) {
        pokesorch.RowData rowData = this.rowDataList.get(position);
        holder.hogeTitle.setText(rowData.hogeTitle);

        //タイプ１の背景設定
       type1name=rowData.hogeContents;
        Drawable type1 = ResourcesCompat.getDrawable(context.getResources(),R.drawable.type1,null);
        typewild sorchty = new typewild();
        String color = sorchty.typoe(type1name);
       type1.setColorFilter(Color.parseColor(color), PorterDuff.Mode.SRC_IN);
        holder.typewaku.setBackground(type1);
        holder.hogeContents.setText(type1name);

        //タイプ２の背景設定
        String type2name = rowData.poketype2;
        holder.type2.setText(type2name);
        Drawable type2d =ResourcesCompat.getDrawable(context.getResources(),R.drawable.type2,null);
        color = sorchty.typoe(type2name);
        type2d.setColorFilter(Color.parseColor(color),PorterDuff.Mode.SRC_IN);
        holder.typewaku2.setBackground(type2d);

        int i = rowData.postion;

        //画像の設定
        Integer ii = Integer.valueOf(i);
        String id = ii.toString();

        //IDのセット
        holder.pokeid.setText("No."+id);

        Bitmap image =  getBitmapFromAsset(id);
        holder.hogeImage.setImageBitmap(image);

        holder.lay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //画面遷移
                String ins[] = {id,rowData.hogeTitle,""};
                Intent intent = new Intent(context, poketouroku.class);
                intent.putExtra("KEY",ins);
                context.startActivity(intent);

            }
        });
    }


    /**
     * リストの行数
     */




    @Override
    public int getItemCount() {
        return rowDataList.size();
    }


    //画像の読み込み
    private Bitmap getBitmapFromAsset(String id) {
        AssetManager assetManager = context.getAssets();
        InputStream istr = null;
        try {
            istr = assetManager.open("sprites/"+id+".png");
        } catch (IOException e) {
            e.printStackTrace();
        }
        Bitmap bitmap = BitmapFactory.decodeStream(istr);
        return bitmap;
    }

    public void type(){


    }
}
