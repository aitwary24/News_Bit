package com.socio.newsbit.Adapter;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.socio.newsbit.Activity.MainActivity;
import com.socio.newsbit.Activity.WebActivity;
import com.socio.newsbit.Model.News;
import com.socio.newsbit.R;
import com.socio.newsbit.utils.OnRecyclerViewItemClickListener;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder>  {
    private List<News> articleArrayList;
    private Context context;
   private OnRecyclerViewItemClickListener onRecyclerViewItemClickListener;
    public NewsAdapter(List<News> articleArrayList) {
        this.articleArrayList = articleArrayList;
    }
    @Override
    public NewsAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_item_news, viewGroup, false);
        return new NewsAdapter.ViewHolder(view);



    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ViewHolder holder, int position) {
        final News articleModel = articleArrayList.get(position);
        if(!TextUtils.isEmpty(articleModel.getTitle())) {
            holder.titleText.setText(articleModel.getTitle());
        }
        if(!TextUtils.isEmpty(articleModel.getDescription())) {
            holder.descriptionText.setText(articleModel.getDescription());
        }
        if(!TextUtils.isEmpty(articleModel.getAuthor())) {
            holder.authorName.setText(articleModel.getAuthor());
        }
        if (!articleModel.getUrlToImage().isEmpty()){
           // holder.coverImage.setImageResource(articleModel.getUrlToImage());
            Picasso.get().load(articleModel.getUrlToImage()).into(holder.coverImage);
        }

        holder.artilceAdapterParentLinear.setTag(articleModel);
    }

    @Override
    public int getItemCount() {
        return articleArrayList.size();
    }




    class ViewHolder extends RecyclerView.ViewHolder{
        private TextView titleText;
        private TextView descriptionText;
        private TextView authorName;
        private ImageView coverImage;
        private LinearLayout artilceAdapterParentLinear;
      ViewHolder(View view) {
            super(view);
            titleText = view.findViewById(R.id.title);
            descriptionText = view.findViewById(R.id.description);
            authorName=view.findViewById(R.id.authorName);
            coverImage=view.findViewById(R.id.coverImage);
            artilceAdapterParentLinear = view.findViewById(R.id.newsAdapterll);


          artilceAdapterParentLinear.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (onRecyclerViewItemClickListener != null) {
                        onRecyclerViewItemClickListener.onItemClick(getAdapterPosition(),view);
                    }
                }
            });
        }
    }
    public void setOnRecyclerViewItemClickListener(OnRecyclerViewItemClickListener onRecyclerViewItemClickListener) {
        this.onRecyclerViewItemClickListener = onRecyclerViewItemClickListener;
    }


}





