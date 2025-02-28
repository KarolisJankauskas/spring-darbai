import { useEffect, useState, useCallback } from "react";
import api from "../../utils/api.js";
import { AdCard } from "../../components/AdCard.jsx";
import { Error } from "../../components/Error.jsx";

export const AdList = () => {
    const [ads, setAds] = useState([]);
    const [currentPage, setCurrentPage] = useState(1);
    const [pageSize, setPageSize] = useState(12);
    const [totalPages, setTotalPages] = useState(12);
    const [error, setError] = useState("");
    const [isLoading, setIsLoading] = useState(false);

   
    const getAdPage = useCallback(async () => {
        setError(""); 
        setIsLoading(true); 
        try {
            const response = await api.get(`/ads`); 
            console.log("API Response:", response.data); 

            
            if (Array.isArray(response.data)) {
                setAds(response.data); 
                setTotalPages(Math.ceil(response.data.length / pageSize)); 
            } else {
                setError("Invalid data format received from the server.");
                console.error("Invalid API response structure:", response.data);
            }
        } catch (error) {
            setError(error.response?.data?.message || error.message || "Failed to fetch ads.");
            console.error("API Error:", error);
        } finally {
            setIsLoading(false); 
        }
    }, [pageSize]);

   
    const onPageSizeChange = async (e) => {
        const newPageSize = parseInt(e.target.value, 10);
        setPageSize(newPageSize);
        setCurrentPage(1); 
    };

   
    const onPaginate = (page) => {
        if (page < 1 || page > totalPages) return; 
        setCurrentPage(page);
    };

    useEffect(() => {
        getAdPage();
    }, [getAdPage]);

   
    const paginatedAds = ads.slice(
        (currentPage - 1) * pageSize,
        currentPage * pageSize
    );

    return (
        <div className="flex flex-col items-center gap-8 p-8">
            {isLoading ? (
                <p className="text-gray-600">Loading ads...</p>
            ) : (
                <>
                    {paginatedAds.length > 0 ? (
                        <ul className="grid grid-cols-1 gap-4 sm:grid-cols-2 lg:grid-cols-3">
                            {paginatedAds.map((ad) => (
                                <AdCard
                                    key={ad.id}
                                    ad={ad}
                                    getAdPage={getAdPage}
                                    currentPage={currentPage}
                                    pageSize={pageSize}
                                />
                            ))}
                        </ul>
                    ) : (
                        <p className="text-gray-600">No ads found.</p>
                    )}

                    <div className="join">
                        <button
                            className="join-item btn"
                            onClick={() => onPaginate(currentPage - 1)}
                            disabled={currentPage === 1 || isLoading}
                        >
                            «
                        </button>
                        <button className="join-item btn">Page {currentPage}</button>
                        <button
                            className="join-item btn"
                            onClick={() => onPaginate(currentPage + 1)}
                            disabled={currentPage === totalPages || isLoading}
                        >
                            »
                        </button>
                        <select
                            value={pageSize}
                            className="join-item select ml-4"
                            onChange={onPageSizeChange}
                            disabled={isLoading}
                        >
                            <option value="6">6</option>
                            <option value="12">12</option>
                            <option value="15">15</option>
                        </select>
                    </div>
                </>
            )}

            {error && <Error error={error} />}
        </div>
    );
};