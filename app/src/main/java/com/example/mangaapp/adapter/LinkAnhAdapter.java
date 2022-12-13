package com.example.mangaapp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mangaapp.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class LinkAnhAdapter extends RecyclerView.Adapter<LinkAnhAdapter.LinkAnhViewHolder> {

    private final List<String> mListLinkAnh;

    public LinkAnhAdapter(List<String> mListLinkAnh) {
        this.mListLinkAnh = mListLinkAnh;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public LinkAnhViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_anh_chapter,parent,false);
        return new LinkAnhViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LinkAnhViewHolder holder, int position) {
        String linkAnh = mListLinkAnh.get(position);
        if(linkAnh == null)
            return;
        Picasso.get().load(mListLinkAnh.get(position))
                .into(holder.imgLinkAnh);
    }

    @Override
    public int getItemCount() {
        if(mListLinkAnh != null)
            return  mListLinkAnh.size();
        return 0;
    }

    public static class LinkAnhViewHolder extends RecyclerView.ViewHolder{

        private final ImageView imgLinkAnh;

        public LinkAnhViewHolder(@NonNull View itemView) {
            super(itemView);
            imgLinkAnh = itemView.findViewById(R.id.img_linkanh);
        }
    }
}
