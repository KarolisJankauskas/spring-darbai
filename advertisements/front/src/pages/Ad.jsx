import { useState, useEffect } from "react";
import api from "../utils/api.js";
import { AdList } from "./ads/AdList.jsx";

export const Ads = () => {
    const [ads, setAds] = useState([]);
    const [searchQuery, setSearchQuery] = useState("");
    const [currentPage, setCurrentPage] = useState(1);
    const [pageSize, setPageSize] = useState(12);
    const [totalPages, setTotalPages] = useState(1);
    const [loading, setLoading] = useState(false);
    const [error, setError] = useState("");

    const fetchAds = async (query = "", page = 1, size = 12) => {
        setLoading(true);
        setError("");
        try {
            const response = await api.get(
                `/ads?search=${query}&page=${page}&size=${size}`
            );
            setAds(response.data.movies); 
            setTotalPages(response.data.totalPages); 
        } catch (error) {
            setError(error.response?.data?.message || error.message || "An error occurred");
        } finally {
            setLoading(false);
        }
    };

    const handleSearchChange = (e) => {
        setSearchQuery(e.target.value);
    };

  
    const handleSearchSubmit = (e) => {
        e.preventDefault();
        fetchAds(searchQuery, 1, pageSize);
        setCurrentPage(1);
    };

    
    const handlePageSizeChange = (e) => {
        const newPageSize = e.target.value;
        setPageSize(newPageSize);
        fetchAds(searchQuery, 1, newPageSize);
        setCurrentPage(1);
    };

    
    const handlePaginate = (page) => {
        if (page < 1 || page > totalPages) return;
        fetchAds(searchQuery, page, pageSize);
        setCurrentPage(page);
    };

    // Fetch movies on component mount and when dependencies change
    useEffect(() => {
        fetchAds(searchQuery, currentPage, pageSize);
    }, [currentPage, pageSize]);

    return (
        <div className="min-h-screen bg-gray-50 p-8">
            <h1 className="text-4xl font-bold text-gray-800 mb-8 text-center">
                Ads
            </h1>

            <form onSubmit={handleSearchSubmit} className="mb-8">
                <div className="flex justify-center">
                    <input
                        type="text"
                        value={searchQuery}
                        onChange={handleSearchChange}
                        placeholder="Search for movies..."
                        className="w-full max-w-md px-4 py-2 border border-gray-300 rounded-l-md focus:ring-blue-500 focus:border-blue-500"
                    />
                    <button
                        type="submit"
                        className="bg-blue-600 text-white px-6 py-2 rounded-r-md hover:bg-blue-700 transition-colors duration-300"
                    >
                        Search
                    </button>
                </div>
            </form>

            {loading && (
                <div className="text-center text-gray-600">Loading ads...</div>
            )}

            {error && (
                <div className="text-center text-red-600 mb-8">{error}</div>
            )}

            {!loading && !error && (
                <>
                    <AdList
                        movies={ads}
                        currentPage={currentPage}
                        pageSize={pageSize}
                        onPageSizeChange={handlePageSizeChange}
                        onPaginate={handlePaginate}
                        totalPages={totalPages}
                    />
                </>
            )}
        </div>
    );
};