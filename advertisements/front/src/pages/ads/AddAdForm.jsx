import { useState, useEffect } from "react";
import api from "../../utils/api.js";
import { useAuth } from "../../context/AuthContext.jsx";
import { useNavigate } from "react-router-dom";

export const AddAdForm = () => {
    const { user } = useAuth();
    const navigate = useNavigate();
    const [formData, setFormData] = useState({
        title: "",
        advertiser: "",
        releaseDate: "",
        imageUrl: "",
        description: "",
        categoryIds: [], 
    });
    const [categories, setCategories] = useState([]);
    const [error, setError] = useState("");
    const [success, setSuccess] = useState("");

  
    useEffect(() => {
        const fetchCategories = async () => {
            try {
                const response = await api.get("/categories");
                setCategories(response.data);
            } catch (error) {
                setError("Failed to fetch categories.");
            }
        };

        fetchCategories();
    }, []);

    const handleChange = (e) => {
        const { name, value } = e.target;
        setFormData({ ...formData, [name]: value });
    };


    const handleCategoryChange = (e) => {
        const { value, checked } = e.target;
        const categoryId = parseInt(value, 10);

        if (checked) {
            setFormData((prev) => ({
                ...prev,
                categoryIds: [...prev.categoryIds, categoryId], 
            }));
        } else {
            setFormData((prev) => ({
                ...prev,
                categoryIds: prev.categoryIds.filter((id) => id !== categoryId), 
            }));
        }
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        setError("");
        setSuccess("");

    
        if (!user) {
            setError("You must be logged in to add ads.");
            return;
        }

        if (!user?.roles?.includes("ROLE_ADMIN")) {
            setError("You do not have permission to add ads.");
            return;
        }

        if (
            !formData.title ||
            !formData.advertiser ||
            !formData.releaseDate ||
            !formData.imageUrl ||
            !formData.description ||
            formData.categoryIds.length === 0
        ) {
            setError("All fields are required, and at least one genre must be selected.");
            return;
        }

        const formattedData = {
            title: formData.title,
            advertiser: formData.advertiser,
            releaseDate: new Date(formData.releaseDate).toISOString().split("T")[0], // Format as YYYY-MM-DD
            imageUrl: formData.imageUrl,
            description: formData.description,
            categories: formData.categoryIds.map((id) => ({ id })),
        };

        console.log("Formatted Data:", formattedData);

        try {
            const response = await api.post("/ads", formattedData);
            console.log("API Response:", response.data);
            setSuccess("Ad added successfully!");

            setFormData({
                title: "",
                advertiser: "",
                releaseDate: "",
                imageUrl: "",
                description: "",
                categoryIds: [],
            });

       
            setTimeout(() => {
                navigate("/ads");
            }, 2000); 
        } catch (error) {
            console.error("API Error:", error.response?.data || error.message); 
            setError(error.response?.data?.message || error.message || "Failed to add ad.");
        }
    };

    return (
        <div className="max-w-md mx-auto p-6 bg-white shadow-md rounded-lg">
            <h2 className="text-2xl font-bold mb-4 text-gray-800">Add a New Ad</h2>
            {error && <div className="text-red-500 mb-4">{error}</div>}
            {success && <div className="text-green-500 mb-4">{success}</div>}
            <form onSubmit={handleSubmit} className="space-y-4">
                <div>
                    <label className="block text-gray-700">Title</label>
                    <input
                        type="text"
                        name="title"
                        value={formData.title}
                        onChange={handleChange}
                        className="w-full px-4 py-2 border rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500"
                        required
                        minLength={2}
                        maxLength={150}
                    />
                </div>

                <div>
                    <label className="block text-gray-700">Advertiser</label>
                    <input
                        type="text"
                        name="director"
                        value={formData.advertiser}
                        onChange={handleChange}
                        className="w-full px-4 py-2 border rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500"
                        required
                        maxLength={150}
                        pattern="^[A-Z][a-z]+( [A-Z][a-z]+)*$" 
                    />
                </div>


                <div>
                    <label className="block text-gray-700">Release Date</label>
                    <input
                        type="date"
                        name="releaseDate"
                        value={formData.releaseDate}
                        onChange={handleChange}
                        className="w-full px-4 py-2 border rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500"
                        required
                        max={new Date().toISOString().split("T")[0]}
                    />
                </div>

                <div>
                    <label className="block text-gray-700">Image URL</label>
                    <input
                        type="url"
                        name="imageUrl"
                        value={formData.imageUrl}
                        onChange={handleChange}
                        className="w-full px-4 py-2 border rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500"
                        required
                        maxLength={150}
                    />
                </div>

                <div>
                    <label className="block text-gray-700">Description</label>
                    <textarea
                        name="description"
                        value={formData.description}
                        onChange={handleChange}
                        className="w-full px-4 py-2 border rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500"
                        required
                        maxLength={65535}
                    />
                </div>

                <div>
                    <label className="block text-gray-700">Categories</label>
                    <div className="space-y-2">
                        {categories.map((category) => (
                            <label key={category.id} className="flex items-center space-x-2">
                                <input
                                    type="checkbox"
                                    name="categories"
                                    value={category.id}
                                    checked={formData.categoryIds.includes(category.id)}
                                    onChange={handleCategoryChange}
                                    className="form-checkbox h-5 w-5 text-blue-600"
                                />
                                <span>{category.name}</span>
                            </label>
                        ))}
                    </div>
                </div>

                <button
                    type="submit"
                    className="w-full bg-blue-600 text-white py-2 rounded-lg hover:bg-blue-700 transition-colors duration-300"
                >
                    Add Ad
                </button>
            </form>
        </div>
    );
};