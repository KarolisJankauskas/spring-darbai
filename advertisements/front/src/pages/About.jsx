export const About = () => {
    return (
        <div className="min-h-screen bg-gray-50 flex flex-col items-center justify-center p-4">
            <h1 className="text-4xl font-bold text-gray-800 mb-4">About Us</h1>
            <p className="text-gray-600 text-lg text-center max-w-2xl mb-8">
            Thank you for visiting Our AdApp !
             We are dedicated to providing innovative solutions to help you .
              Our team is committed to continuous improvement and user satisfaction.
               Stay connected and be sure to check for updates!
            </p>
            <div className="grid grid-cols-1 md:grid-cols-2 gap-8">
                <div className="bg-white p-6 rounded-lg shadow-md">
                    <h2 className="text-2xl font-bold text-gray-800 mb-4">Our Mission</h2>
                    <p className="text-gray-600">
                        To provide a comprehensive and user-friendly platform.
                    </p>
                </div>
                <div className="bg-white p-6 rounded-lg shadow-md">
                    <h2 className="text-2xl font-bold text-gray-800 mb-4">Our Vision</h2>
                    <p className="text-gray-600">
                        To become the go-to destination for ads.
                        
                    </p>
                </div>
            </div>
        </div>
    );
};