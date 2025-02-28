import { useState } from "react";
import { useAuth } from "../context/AuthContext.jsx";
import { NavLink } from "react-router-dom";
import api from "../utils/api.js";
import { Error } from "./Error.jsx";

export const AdCard = (props) => {
    const { ad, getAdPage, currentPage, pageSize } = props;
    const [error, setError] = useState("");
    const { user } = useAuth();

    if (!ad) {
        return <div className="text-gray-600">No ad data available.</div>;
    }

    const { id, imageUrl = "", title = "", advertiser = "", releaseDate = "", categories = [], description = "" } = ad;

    const deleteAd = async () => {
        try {
            await api.delete(`/ads/${id}`);
            await getAdPage(pageSize, currentPage);
        } catch (error) {
            setError(error.response?.data?.message || error.message || "An error occurred");
        }
    };

    return (
        <div className="card card-side bg-base-100 shadow-lg hover:shadow-xl transition-shadow duration-300">
            <figure className="w-1/3">
                <img
                    src={imageUrl || "https://via.placeholder.com/150"}
                    alt={`${title} Thumbnail`}
                    className="w-full h-full object-cover"
                    onError={(e) => {
                        e.target.src = "https://via.placeholder.com/150";
                    }}
                />
            </figure>
            <div className="card-body w-2/3 p-4">
                <h2 className="card-title text-2xl font-bold text-gray-800">{title}</h2>
                <p className="text-gray-600">Advertiser: {advertiser}</p>
                <p className="text-gray-600">Release Date: {new Date(releaseDate).toLocaleDateString()}</p>
                {description && <p className="text-gray-600">{description}</p>}
                {categories.length > 0 && (
                    <div className="mt-2">
                        <p className="text-gray-600 font-semibold">Categories:</p>
                        <div className="flex flex-wrap gap-2">
                            {categories.map((category) => (
                                <span
                                    key={category.id}
                                    className="bg-blue-100 text-blue-800 text-sm px-2 py-1 rounded-full"
                                >
                                    {category.name}
                                </span>
                            ))}
                        </div>
                    </div>
                )}

                <div className="card-actions mt-4">
                    <NavLink
                        to={`/ads/view/${id}`}
                        className="btn btn-primary bg-blue-600 hover:bg-blue-700 text-white"
                    >
                        View
                    </NavLink>
                    {user?.roles?.includes("ROLE_ADMIN") && (
                        <>
                            <NavLink
                                to="/admin/add-ad"
                                className="btn btn-success bg-green-600 hover:bg-green-700 text-white"
                            >
                                Add
                            </NavLink>
                            <button
                                onClick={deleteAd}
                                className="btn btn-error bg-red-600 hover:bg-red-700 text-white"
                            >
                                Delete
                            </button>
                        </>
                    )}
                </div>
                {error && <Error error={error} />}
            </div>
        </div>
    );
};


AdCard.defaultProps = {
    ad: {},
};