package com.mlaffan.giftpal;

import android.app.ActionBar;
import android.app.FragmentTransaction;
import android.app.ListFragment;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.Toast;

import com.mlaffan.giftpal.sqlite.adapter.EventAdapter;
import com.mlaffan.giftpal.sqlite.adapter.PersonAdapter;
import com.mlaffan.giftpal.sqlite.db.DatabaseHelper;
import com.mlaffan.giftpal.sqlite.db.Person;

import java.util.ArrayList;

public class MainActivity extends FragmentActivity implements ActionBar.TabListener {
    public PersonAdapter personAdapter;

    AppSectionsPagerAdapter mAppSectionsPagerAdapter;

    ViewPager mViewPager;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DatabaseHelper dbHelp = new DatabaseHelper(getApplicationContext());

        // Create the adapter that will return a fragment for each of the four primary sections
        // of the app.
        mAppSectionsPagerAdapter = new AppSectionsPagerAdapter(getSupportFragmentManager());

        // Set up the action bar.
        final ActionBar actionBar = getActionBar();

        // Specify that the Home/Up button should not be enabled, since there is no hierarchical
        // parent.
        actionBar.setHomeButtonEnabled(false);

        // Specify that we will be displaying tabs in the action bar.
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        // Set up the ViewPager, attaching the adapter and setting up a listener for when the
        // user swipes between sections.
        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(mAppSectionsPagerAdapter);
        mViewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                // When swiping between different app sections, select the corresponding tab.
                // We can also use ActionBar.Tab#select() to do this if we have a reference to the
                // Tab.
                actionBar.setSelectedNavigationItem(position);
            }
        });

        // For each of the sections in the app, add a tab to the action bar.
        for (int i = 0; i < mAppSectionsPagerAdapter.getCount(); i++) {
            // Create a tab with text corresponding to the page title defined by the adapter.
            // Also specify this Activity object, which implements the TabListener interface, as the
            // listener for when this tab is selected.
            actionBar.addTab(
                    actionBar.newTab()
                            .setText(mAppSectionsPagerAdapter.getPageTitle(i))
                            .setTabListener(this));
        }
    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
    }

    @Override
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
        // When the given tab is selected, switch to the corresponding page in the ViewPager.
        mViewPager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to one of the primary
     * sections of the app.
     */
    public static class AppSectionsPagerAdapter extends FragmentPagerAdapter {

        public AppSectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int i) {
            switch (i) {
                case 0:
                    return new EventsTickerFragment();

                case 1:
                    return new AddPeopleFragment();

                case 2:
                    return new PeopleSectionFragment();

                default:
                    return new SnapGiftFragment();
            }
        }

        @Override
        public int getCount() {
            return 4;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            if (position == 0) {
                return "Events";
            } else if (position == 1) {
                return "Add People";
            } else if (position == 2) {
                return "Your People";
            } else {
                return "Snap Gift";
            }
        }
    }

    /**
     * A fragment that an events ticker.
     */
    public static class EventsTickerFragment extends Fragment {
        public static EventAdapter adapter;

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_ticker, container, false);
            return rootView;
        }

        @Override
        public void onViewCreated(View view, Bundle bundle) {
            super.onViewCreated(view, bundle);
            DatabaseHelper dbHelp = new
                    DatabaseHelper(getActivity().getApplicationContext());
            ArrayList<Person> event = dbHelp.getPeople();
            adapter = new EventAdapter(getActivity().getApplicationContext(),
                    R.layout.fragment_ticker_list_item, event);
            ListView listView = (ListView) getActivity().findViewById(R.id.eventList);
            listView.setAdapter(adapter);
        }


    }

    /**
     * A fragment lets the user create a new profile.
     */
    public static class AddPeopleFragment extends Fragment {
        private String sex = "male";
        private EditText firstName;
        private EditText lastName;
        private DatePicker birthday;

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_add_people, container, false);
            return rootView;
        }

        @Override
        public void onViewCreated(View view, final Bundle bundle) {
            super.onViewCreated(view, bundle);
            firstName = (EditText) view.findViewById(R.id.firstName);
            lastName = (EditText) view.findViewById(R.id.lastName);
            RadioButton male = (RadioButton) view.findViewById(R.id.male);
            RadioButton female = (RadioButton) view.findViewById(R.id.female);
            birthday = (DatePicker) view.findViewById(R.id.datePicker);
            Button button = (Button) view.findViewById(R.id.updateButton);

            male.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    sex = "male";
                }
            });

            female.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    sex = "female";
                }
            });

            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String fName = firstName.getText().toString();
                    String lName = lastName.getText().toString();
                    int day = birthday.getDayOfMonth();
                    int month = birthday.getMonth();
                    int year = birthday.getYear();
                    Person person = new Person();
                    person.setFirstName(fName);
                    person.setLastName(lName);
                    person.setSex(sex);
                    person.setBirthday("" + day + "/" + month + "/" + year);
                    DatabaseHelper dbHelper = new DatabaseHelper(getActivity().getApplicationContext());
                    dbHelper.addPerson(person);
                    Toast.makeText(getActivity(), "Profile added!", Toast.LENGTH_SHORT).show();

                    if (PeopleSectionFragment.adapter != null) {
                        PeopleSectionFragment.adapter.add(person);
                    }
                }
            });
        }
    }

    /**
     * A fragment that contains a list of profiles.
     */
    public static class PeopleSectionFragment extends Fragment {
        public static PersonAdapter adapter;

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_people, container, false);
            return rootView;
        }

        @Override
        public void onViewCreated(View view, Bundle bundle) {
            super.onViewCreated(view, bundle);
            DatabaseHelper dbHelp = new
                    DatabaseHelper(getActivity().getApplicationContext());
            ArrayList<Person> people = dbHelp.getPeople();
            adapter = new PersonAdapter(getActivity().getApplicationContext(),
                    R.layout.fragment_people_list_item, people);
            ListView listView = (ListView) getActivity().findViewById(R.id.list);
            listView.setAdapter(adapter);
            listView.setOnItemClickListener(new ItemClicked());
        }

        class ItemClicked implements AdapterView.OnItemClickListener {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Person person = (Person)adapter.getItem(position);
                //Log.i("ItemClicked", person.getFirstName());

                // When a listitem is clicked
                Intent intent = new Intent(getActivity(), Profile.class);
                intent.putExtra("person", person);
                startActivity(intent);
            }
        }
    }

    public static class SnapGiftFragment extends Fragment {

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_snap_gift, container, false);

            rootView.findViewById(R.id.demo_external_activity)
                    .setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent externalActivityIntent = new Intent(Intent.ACTION_PICK);
                            externalActivityIntent.setType("image/*");
                            externalActivityIntent.addFlags(
                                    Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
                            startActivity(externalActivityIntent);
                        }
                    });

            return rootView;
        }
    }
}
