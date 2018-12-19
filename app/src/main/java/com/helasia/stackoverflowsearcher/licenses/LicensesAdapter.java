package com.helasia.stackoverflowsearcher.licenses;

import android.animation.Animator;
import android.content.Context;
import android.os.Build.VERSION_CODES;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.helasia.stackoverflowsearcher.R;
import com.helasia.stackoverflowsearcher.data.model.License;
import java.util.List;

public class LicensesAdapter extends RecyclerView.Adapter<LicensesAdapter.ViewHolder> {
  public Context context;
  private List<License> licensesList;

  LicensesAdapter(Context context, List<License> licensesList) {
    this.context = context;
    this.licensesList = licensesList;
    setHasStableIds(true);
  }


  @Override
  public LicensesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
    LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
    View v = layoutInflater.inflate(R.layout.one_license_card, parent, false);
    return new LicensesAdapter.ViewHolder(v);
  }

  @Override
  public void onBindViewHolder(final LicensesAdapter.ViewHolder viewHolder, final int position) {
    if(viewHolder != null){
      viewHolder.bind(licensesList.get(position));

      viewHolder.licenseCardViewLayout.setOnClickListener(view ->
              ((LicensesActivity)context).goToLicenseSource(licensesList.get(position).getLicenseUrl()));
    }
  }

  @Override
  public int getItemCount() {
    return licensesList.size();
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
  public void onViewAttachedToWindow(LicensesAdapter.ViewHolder viewHolder) {
    super.onViewAttachedToWindow(viewHolder);
    animateCircularReveal(viewHolder.itemView);
  }

  @Override
  public long getItemId(int position) {
    return licensesList.get(position).getLicenseId();
  }

  public class ViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.licenseName)
    TextView licenseNameTextView;
    @BindView(R.id.licenseAuthor) TextView licenseAuthorTextView;
    @BindView(R.id.licenseDescription) TextView licenseDescriptionTextView;
    @BindView(R.id.my_license_card_view_layout)
    CardView licenseCardViewLayout;

    public ViewHolder(View v) {
      super(v);
      ButterKnife.bind(this, v);
    }

    void bind(License license) {
      String licenseName = license.getLicenseName();
      String licenseAuthor = license.getLicenseAuthor();
      String licenseDescription = license.getLicenseDescription();

      licenseNameTextView.setText(licenseName);
      licenseAuthorTextView.setText(licenseAuthor);
      licenseDescriptionTextView.setText(licenseDescription);
    }
  }
}
