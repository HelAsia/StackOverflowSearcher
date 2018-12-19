package com.helasia.stackoverflowsearcher.licenses;

import com.helasia.stackoverflowsearcher.R;
import com.helasia.stackoverflowsearcher.data.model.License;
import java.util.ArrayList;
import java.util.List;

public class LicensesPresenter implements LicensesContract.Presenter {
  private LicensesContract.View licensesView;

  public LicensesPresenter(LicensesContract.View licensesView){
    this.licensesView = licensesView;
  }

  @Override
  public void setFirstScreen() {
    licensesView.setToolbar();
    licensesView.setRecyclerView(getLicensesList());
  }

  private List<License> getLicensesList(){
    List<License> licensesList = new ArrayList<>();
    License licenseOne = new License(1,
        licensesView.getContext().getResources().getString(R.string.license_one_name),
        licensesView.getContext().getResources().getString(R.string.license_one_author),
        licensesView.getContext().getResources().getString(R.string.license_one_description),
        licensesView.getContext().getResources().getString(R.string.license_one_url));
    License licenseTwo = new License(2,
        licensesView.getContext().getResources().getString(R.string.license_two_name),
        licensesView.getContext().getResources().getString(R.string.license_two_author),
        licensesView.getContext().getResources().getString(R.string.license_two_description),
        licensesView.getContext().getResources().getString(R.string.license_two_url));
    License licenseThree = new License(3,
        licensesView.getContext().getResources().getString(R.string.license_three_name),
        licensesView.getContext().getResources().getString(R.string.license_three_author),
        licensesView.getContext().getResources().getString(R.string.license_three_description),
        licensesView.getContext().getResources().getString(R.string.license_three_url));
    License licenseFour = new License(4,
        licensesView.getContext().getResources().getString(R.string.license_four_name),
        licensesView.getContext().getResources().getString(R.string.license_four_author),
        licensesView.getContext().getResources().getString(R.string.license_four_description),
        licensesView.getContext().getResources().getString(R.string.license_four_url));
    License licenseFive = new License(5,
        licensesView.getContext().getResources().getString(R.string.license_five_name),
        licensesView.getContext().getResources().getString(R.string.license_five_author),
        licensesView.getContext().getResources().getString(R.string.license_five_description),
        licensesView.getContext().getResources().getString(R.string.license_five_url));
    License licenseSix = new License(6,
        licensesView.getContext().getResources().getString(R.string.license_six_name),
        licensesView.getContext().getResources().getString(R.string.license_six_author),
        licensesView.getContext().getResources().getString(R.string.license_six_description),
        licensesView.getContext().getResources().getString(R.string.license_six_url));
    License licenseSeven = new License(7,
        licensesView.getContext().getResources().getString(R.string.license_seven_name),
        licensesView.getContext().getResources().getString(R.string.license_seven_author),
        licensesView.getContext().getResources().getString(R.string.license_seven_description),
        licensesView.getContext().getResources().getString(R.string.license_seven_url));
    License licenseEight = new License(8,
        licensesView.getContext().getResources().getString(R.string.license_eight_name),
        licensesView.getContext().getResources().getString(R.string.license_eight_author),
        licensesView.getContext().getResources().getString(R.string.license_eight_description),
        licensesView.getContext().getResources().getString(R.string.license_eight_url));

    licensesList.add(licenseOne);
    licensesList.add(licenseTwo);
    licensesList.add(licenseThree);
    licensesList.add(licenseFour);
    licensesList.add(licenseFive);
    licensesList.add(licenseSix);
    licensesList.add(licenseSeven);
    licensesList.add(licenseEight);

    return licensesList;
  }
}
