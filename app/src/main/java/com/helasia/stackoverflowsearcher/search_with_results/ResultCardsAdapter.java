package com.helasia.stackoverflowsearcher.search_with_results;

import android.animation.Animator;
import android.content.Context;
import android.os.Build.VERSION_CODES;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewAnimationUtils;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.helasia.stackoverflowsearcher.R;
import com.helasia.stackoverflowsearcher.data.model.Item;
import com.helasia.stackoverflowsearcher.data.model.QueryResult;
import com.helasia.stackoverflowsearcher.utils.Constant;
import com.squareup.picasso.Picasso;
import java.util.List;

public class ResultCardsAdapter extends RecyclerView.Adapter<ResultCardsAdapter.ViewHolder> {
  private Context context;
  private List<Item> itemList;
  private OnShareWebViewDetailsListener callbackWebView;

  public void setCallbackWebViewOnShareClickedListener(OnShareWebViewDetailsListener callbackWebView){
    this.callbackWebView = callbackWebView;
  }

  public interface OnShareWebViewDetailsListener{
    void shareCardClicked(String url);
  }

  public ResultCardsAdapter(Context context, List<Item> itemList){
    this.context = context;
    this.itemList = itemList;
    setHasStableIds(true);
  }

  @NonNull
  @Override
  public ResultCardsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
    LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
    View v = layoutInflater.inflate(R.layout.one_result_card, parent, false);
    return new ResultCardsAdapter.ViewHolder(v);
  }

  @Override
  public void onBindViewHolder(@NonNull ResultCardsAdapter.ViewHolder viewHolder, final int position) {
    viewHolder.bind(itemList.get(position));

    viewHolder.cardViewLayout.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View view) {
        String url = itemList.get(position).getLink();
        callbackWebView.shareCardClicked(url);
      }
    });
  }

  @Override
  public int getItemCount() {
    return itemList.size();
  }

  @RequiresApi(api = VERSION_CODES.LOLLIPOP)
  public void animateCircularReveal(View view) {
    int centerX = 0;
    int centerY = 0;
    int startRadius = 0;
    int endRadius = Math.max(view.getWidth(), view.getHeight());
    Animator animation = ViewAnimationUtils
        .createCircularReveal(view, centerX, centerY, startRadius, endRadius);
    view.setVisibility(View.VISIBLE);
    animation.start();
  }

  @RequiresApi(api = VERSION_CODES.LOLLIPOP)
  @Override
  public void onViewAttachedToWindow(ResultCardsAdapter.ViewHolder viewHolder) {
    super.onViewAttachedToWindow(viewHolder);
    animateCircularReveal(viewHolder.itemView);
  }

  public class ViewHolder extends RecyclerView.ViewHolder{
    @BindView(R.id.one_result_card_layout) CardView cardViewLayout;
    @BindView(R.id.title) TextView titleView;
    @BindView(R.id.user_name) TextView userNameView;
    @BindView(R.id.avatar) ImageView avatarView;
    @BindView(R.id.answer_count) TextView answerCountView;

    public ViewHolder(View v) {
      super(v);
      ButterKnife.bind(this, v);
    }

    void bind(Item item){
      String title = item.getTitle();
      String userName = item.getOwner().getDisplayName();
      String avatarUrl = item.getOwner().getProfileImage();
      int answerCount = item.getAnswerCount();

      titleView.setText(title);
      userNameView.setText(userName);
      Picasso.get().load(avatarUrl).into(avatarView);
      answerCountView.setText(String.valueOf(answerCount));

    }
  }
}
