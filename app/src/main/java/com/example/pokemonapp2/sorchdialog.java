package com.example.pokemonapp2;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

public class sorchdialog extends DialogFragment {
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        // カスタムレイアウトの用意
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        View customAlertView = inflater.inflate(R.layout.custom_dialog, null);

        // タイトルの変更
        TextView title = customAlertView.findViewById(R.id.title);
        title.setText("こんにちは！");

        // メッセージの変更
        TextView message = customAlertView.findViewById(R.id.message);
        message.setText("ここにメッセージを入力します。ここにメッセージを入力します。ここにメッセージを入力します。");

        // ダイアログの作成
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(customAlertView)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // このボタンを押した時の処理を書きます。
                    }
                });
        return builder.create();
    }

}

