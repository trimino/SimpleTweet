package com.codepath.apps.restclienttemplate;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.codepath.apps.restclienttemplate.models.Tweet;


import java.util.List;

public class TweetsAdapter extends RecyclerView.Adapter<TweetsAdapter.ViewHolder>{

    Context context;
    List<Tweet> tweets;

    public TweetsAdapter(Context context, List<Tweet> tweets) {
        this.context = context;
        this.tweets = tweets;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_tweet, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Tweet tweet = tweets.get(position);
        holder.bind(tweet);
    }

    @Override
    public int getItemCount() {
        return tweets.size();
    }

    public void clear(){
        tweets.clear();
        notifyDataSetChanged();
    }

    // Clean all elements of the recycler
    public void addAll(List<Tweet> tweetList){
        tweets.addAll(tweetList);
        notifyDataSetChanged();
    }

    // Add a list of items -- change to type used
    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView ivProfileImage;
        ImageView ivTweetedImage;
        TextView tvBody;
        TextView tvScreenName;
        TextView tvName;
        TextView tvCreatedAt;
        TextView tvLikes;
        TextView tvRetweets;

        public ViewHolder(@NonNull View itemView){
            super(itemView);
            ivProfileImage = itemView.findViewById(R.id.ivProfileImage);
            ivTweetedImage = itemView.findViewById(R.id.ivTweetedImage);
            tvScreenName = itemView.findViewById(R.id.tvScreenName);
            tvBody = itemView.findViewById(R.id.tvBody);
            tvName = itemView.findViewById(R.id.tvName);
            tvCreatedAt = itemView.findViewById(R.id.tvCreatedAt);
            tvLikes = itemView.findViewById(R.id.tvLikes);
            tvRetweets = itemView.findViewById(R.id.tvRetweets);
        }

        public void bind(Tweet tweet) {
            tvBody.setText(tweet.body);
            tvScreenName.setText(tweet.user.screenName);
            tvName.setText(String.format("@%s", tweet.user.name));
            tvCreatedAt.setText(tweet.createdAt);
            tvRetweets.setText(String.format("%s", tweet.retweets));
            tvLikes.setText(String.format("%s", tweet.likes));

            // Check to see if the tweet has a single photo
            if (tweet.media.hasMedia){
                Glide.with(context).load(tweet.media.mediaHTTPS).into(ivTweetedImage);
            }
            Glide.with(context).load(tweet.user.profileImageUrl)
                    .transform(new CircleCrop()).into(ivProfileImage);
        }
    }
}
