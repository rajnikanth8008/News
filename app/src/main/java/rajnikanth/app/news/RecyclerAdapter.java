package rajnikanth.app.news;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

import rajnikanth.app.news.model.Articles;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyviewHolder> {

    Context context;
    List<Articles> articalsList;

    public RecyclerAdapter(Context context, List<Articles> articalsList) {
        this.context = context;
        this.articalsList = articalsList;
    }

    public void setArticalsList(List<Articles> articalsList) {
        this.articalsList = articalsList;
        notifyDataSetChanged();
    }

    @Override
    public RecyclerAdapter.MyviewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recycler_layout,parent,false);
        return new MyviewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerAdapter.MyviewHolder holder, final int position) {
        holder.tvTitle.setText(articalsList.get(position).getTitle());
        holder.tvDescription.setText(articalsList.get(position).getDescription());
        holder.tv_publishedat.setText(articalsList.get(position).getPublishedAt());
        Glide.with(context).load(articalsList.get(position).getUrlToImage())
                .apply(RequestOptions.centerCropTransform())
                .override(1000,500)
                .into(holder.image);
        holder.tvTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, WebActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("Url",articalsList.get(position).getUrl());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        if(articalsList != null){
            return articalsList.size();
        }
        return 0;

    }

    public class MyviewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle,tvDescription,tv_publishedat;
        ImageView image;

        public MyviewHolder(View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tv_title);
            tvDescription = itemView.findViewById(R.id.tv_description);
            image = itemView.findViewById(R.id.tv_image);
            tv_publishedat = itemView.findViewById(R.id.tv_publishedat);
        }
    }
}
