package com.appsoluteperfection.graphitemobile;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v4.app.FragmentManager;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.widget.DrawerLayout;
import android.view.inputmethod.InputMethodManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.HeaderViewListAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.appsoluteperfection.graphitemobile.NavigationDrawerFragment;
import com.appsoluteperfection.graphitewear.entities.Graph;
import com.appsoluteperfection.graphitewear.queries.GraphiteQuery;
import com.appsoluteperfection.graphitewear.queries.HistoricalQueryCollection;
import com.google.inject.Inject;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;

import roboguice.RoboGuice;
import roboguice.activity.RoboActionBarActivity;
import roboguice.fragment.RoboFragment;
import roboguice.inject.InjectView;


public class MainActivity extends RoboActionBarActivity
        implements NavigationDrawerFragment.NavigationDrawerCallbacks {


    /**
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     */
    private NavigationDrawerFragment mNavigationDrawerFragment;

    /**
     * Used to store the last screen title. For use in {@link #restoreActionBar()}.
     */
    private CharSequence mTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);
        mTitle = getTitle();

        // Set up the drawer.
        mNavigationDrawerFragment.setUp(
                R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout));

    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {
        // update the main content by replacing fragments
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.container, PlaceholderFragment.newInstance(position + 1))
                .commit();
    }

    public void onSectionAttached(int number) {
        switch (number) {
            case 1:
                mTitle = getString(R.string.title_last_search_results_section);
                break;
            case 2:
                mTitle = getString(R.string.title_graph_section);
                break;
            case 3:
                mTitle = getString(R.string.title_search_history_section);
                break;
        }
    }

    public void restoreActionBar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle(mTitle);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!mNavigationDrawerFragment.isDrawerOpen()) {
            // Only show items in the action bar relevant to this screen
            // if the drawer is not showing. Otherwise, let the drawer
            // decide what to show in the action bar.
            getMenuInflater().inflate(R.menu.menu_main, menu);
            restoreActionBar();
            return true;
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends RoboFragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";
        private static final String ARG_SEARCH_STRING = "search_string";

        @InjectView(R.id.searchText)
        EditText searchText;
        @InjectView(R.id.btnSearch)
        ImageButton btnSearch;
        @InjectView(R.id.webViewGraph)
        WebView webViewGraph;
        @InjectView(R.id.txtResults)
        TextView txtResults;
        @InjectView(R.id.list)
        ListView listView;

        @Inject
        GraphiteQuery _graphQuery;
        @Inject
        HistoricalQueryCollection _history;


        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        public PlaceholderFragment() {
        }

        @Override
        public void onViewCreated(View view, Bundle savedInstanceState) {
            super.onViewCreated(view, savedInstanceState);
            RoboGuice.getInjector(getActivity()).injectViewMembers(this);
            ArrangeUI();
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            return rootView;
        }

        private void ArrangeUI() {
            int section = getIntExtra(ARG_SECTION_NUMBER);
            String searchString = getStringExtra(ARG_SEARCH_STRING);

            searchText.setText(searchString);
            btnSearch.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // TODO, introduce an enum
                    setSectionView(1);
                    setGraphsFromSearchText();
                    hideKeyboard();
                }
            });
            setSectionView(section);
        }

        private Collection<Graph> _graphs;
        private Collection<Graph> getGraphs() {
            if (_graphs != null) {
                return _graphs;
            }
            return new LinkedList<>();
        }

        private Graph _graph;
        private void setGraph(int position){
            Graph[] graphs = getGraphs().toArray(new Graph[0]);
            _graph = graphs[position];
            setSectionView(2);
            String imageUrl = _graph.getImageUrl();
            webViewGraph.getSettings().setJavaScriptEnabled(true);
            webViewGraph.getSettings().setPluginState(WebSettings.PluginState.ON);
            webViewGraph.setWebViewClient(new WebViewClient());
            webViewGraph.loadUrl(imageUrl);
            _history.add(_graph);
        }

        private String[] getGraphTitles() {
            // TODO, get this to work with just graphs
            Graph[] graphs = getGraphs().toArray(new Graph[0]);
            String[] ret = new String[graphs.length];
            for (int i = 0; i < ret.length; i++) {
                ret[i] = graphs[i].getId();
            }
            return ret;
        }

        private void setGraphsFromSearchText() {
            String searchString = searchText.getText().toString();
            _graphs = _graphQuery.getGraphFromSearchString(searchString);
            setUIFromGraphs();
        }

        private void setGraphsFromHistory() {
            _graphs = _history.getAll();
            setUIFromGraphs();
        }

        private void setUIFromGraphs() {
            String[] graphTitles = getGraphTitles();
            txtResults.setText("" + graphTitles.length + " result(s) found");
            listView.setAdapter(new ArrayAdapter<>(getActivity(),
                    android.R.layout.simple_list_item_1, graphTitles));
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view,
                                        int position, long id) {
                    setGraph(position);
                }
            });
        }

        private void hideKeyboard(){
            InputMethodManager imm =
                    (InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(searchText.getWindowToken(), 0);
        }

        private String getStringExtra(String key) {
            return getArguments().getString(key);
        }

        private int getIntExtra(String key) {
            return getArguments().getInt(key);
        }

        private void setSectionView(int section) {
            // TODO enum
            // TODO MVP pattern
            switch (section) {
                // TODO, extract 0 and 2 to be a method, different from 1
                case 1: // Search
                    listView.setVisibility(View.VISIBLE);
                    txtResults.setVisibility(View.VISIBLE);
                    webViewGraph.setVisibility(View.GONE);
                    break;
                case 2: // Graph
                    listView.setVisibility(View.GONE);
                    txtResults.setVisibility(View.GONE);
                    webViewGraph.setVisibility(View.VISIBLE);

                    break;
                case 3: // History
                    listView.setVisibility(View.VISIBLE);
                    txtResults.setVisibility(View.VISIBLE);
                    webViewGraph.setVisibility(View.GONE);
                    setGraphsFromHistory();
                    break;
                default:
                    throw new IllegalArgumentException("Invalid section provided: " + section);
            }
        }

        @Override
        public void onAttach(Activity activity) {
            super.onAttach(activity);
            ((MainActivity) activity).onSectionAttached(
                    getArguments().getInt(ARG_SECTION_NUMBER));
        }
    }

}
