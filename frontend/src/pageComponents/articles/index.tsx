'use client';

import Contents from './components/contents';
import SearchBar from './components/searchbar';
import SideBar from './components/sidebar';

const Articles = () => {
    return (
        <>
            <Contents />
            <SearchBar />
            <SideBar />
        </>
    );
};

export default Articles;
